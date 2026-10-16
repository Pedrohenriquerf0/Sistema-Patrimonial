package sys.patrimonio.view.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import sys.patrimonio.exceptions.PatrimonioExistenteException;
import sys.patrimonio.exceptions.PersistenciaException;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.service.PatrimoniadoManager;
import sys.patrimonio.util.ComboBoxHelper;
import sys.patrimonio.util.SeletorFoto;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerCadastrarPatrimoniado implements Initializable {
    @FXML
    private ControllerSidebar sidebarController;
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoTombo;
    @FXML
    private TextField campoNumeroSerie;
    @FXML
    private TextField campoCategoria;
    @FXML
    private ComboBox<Localidade> comboLocalidade;
    @FXML
    private ComboBox<Status> comboStatus;
    @FXML
    private TextArea campoDescricao;

    @FXML
    private ImageView imagemPatrimonio;

    @FXML
    private Label labelSemFoto;

    private File arquivoImagemSelecionado;

    @FXML
    private void btt_buscarFoto(ActionEvent event) {
        File file = SeletorFoto.escolher(imagemPatrimonio.getScene().getWindow());

        if (file != null) {
            arquivoImagemSelecionado = file;
            imagemPatrimonio.setImage(SeletorFoto.carregarReduzida(file,272));
            labelSemFoto.setVisible(false);
        }
    }

    @FXML
    private void btt_limpar(ActionEvent event) {
        if (Alertas.confirmar("Deseja Limpar?")) {
            limpar();
        }
    }

    private void limpar() {
        campoNome.clear();
        campoTombo.clear();
        campoNumeroSerie.clear();
        campoDescricao.clear();
        campoCategoria.clear();
        comboLocalidade.getSelectionModel().clearSelection();
        comboStatus.getSelectionModel().clearSelection();
        imagemPatrimonio.setImage(null);
        labelSemFoto.setVisible(true);
        arquivoImagemSelecionado = null;
        imagemPatrimonio.setImage(null);

        ComboBoxHelper.atualizarCombox(comboLocalidade, comboStatus);
    }

    private boolean validarFormulario() {
        List<String> faltando = new ArrayList<>();

        if (campoNome.getText().isBlank()) faltando.add("Nome");
        if (campoTombo.getText().isBlank()) faltando.add("Número do tombo");
        if (campoNumeroSerie.getText().isBlank()) faltando.add("Número de série");
        if (campoCategoria.getText().isBlank()) faltando.add("Categoria");
        if (comboLocalidade.getValue() == null) faltando.add("Localidade");
        if (comboStatus.getValue() == null) faltando.add("Status");
        if (arquivoImagemSelecionado == null) faltando.add("Foto");
        if (campoDescricao.getText().isBlank()) faltando.add("Descrição");

        if (!faltando.isEmpty()) {
            Alertas.aviso("Preencha: " + String.join(", ", faltando) + ".");
            return false;
        }
        return true;
    }

    @FXML
    private void btt_salvar(ActionEvent event) {

        if (validarFormulario()) {
            String nome = campoNome.getText();
            String tombo = campoTombo.getText();
            String numeroSerie = campoNumeroSerie.getText();
            String categoria = campoCategoria.getText();
            String descricao = campoDescricao.getText();
            Localidade localidade = comboLocalidade.getValue();
            Status status = comboStatus.getValue();

            ItemPatrimoniado patrimoniado = new ItemPatrimoniado(nome, tombo, numeroSerie, descricao, localidade, categoria, status, arquivoImagemSelecionado.getPath());
            PatrimoniadoManager patrimoniadoManager = new PatrimoniadoManager();

            try {
                patrimoniadoManager.salvar(patrimoniado);
                Alertas.aviso("Salvo com sucesso");
                limpar();
            } catch (PatrimonioExistenteException e) {
                Alertas.aviso("Já existe um Patrimonio com esse tombo.");
            } catch (IllegalArgumentException e) {
                Alertas.aviso(e.getMessage());
            } catch (PersistenciaException e) {
                e.printStackTrace();
                Alertas.erro("Não foi possível salvar. Tente novamente.");
            }

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebarController.ativarCadastroPatrimonio();
        ComboBoxHelper.carregarLocalidade(comboLocalidade);
        ComboBoxHelper.carregarStatus(comboStatus);
    }
}
