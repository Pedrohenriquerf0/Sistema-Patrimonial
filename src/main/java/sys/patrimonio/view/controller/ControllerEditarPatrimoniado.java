package sys.patrimonio.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sys.patrimonio.exceptions.PatrimonioExistenteException;
import sys.patrimonio.exceptions.PersistenciaException;
import sys.patrimonio.model.Item;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.service.PatrimoniadoManager;
import sys.patrimonio.util.LoaderTelas;
import sys.patrimonio.util.PatrimonioLoader;
import sys.patrimonio.util.SeletorFoto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

;

public class ControllerEditarPatrimoniado implements DataBridgePatrimonio {
    private ItemPatrimoniado patrimoniadoClass;
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoTombo;
    @FXML
    private TextField campoNumeroSerie;
    @FXML
    private TextField campoCategoria;
    @FXML
    private TextField campoLocalidade;
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
    public void btt_buscarFoto() {
        File file = SeletorFoto.escolher(imagemPatrimonio.getScene().getWindow());

        if (file != null) {
            arquivoImagemSelecionado = file;
            imagemPatrimonio.setImage(SeletorFoto.carregarReduzida(file, 272));
            labelSemFoto.setVisible(false);
        }
    }

    @FXML
    public void btt_cancelar(ActionEvent event) {
        boolean resposta = Alertas.confirmar("Deseja Cancelar?");
        if (resposta) {
            voltarDetalhes(patrimoniadoClass, event);
        }
    }

    private void voltarDetalhes(Item item, ActionEvent event) {
        ControllerItemPatrimoniado controllerItemPatrimoniado = new ControllerItemPatrimoniado();
        LoaderTelas.loaderTela(item, event, controllerItemPatrimoniado, "view/ItemPatrimoniado.fxml");
    }

    private boolean validarFormulario() {
        List<String> faltando = new ArrayList<>();

        if (campoNome.getText().isBlank()) faltando.add("Nome");
        if (campoCategoria.getText().isBlank()) faltando.add("Categoria");
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
    public void btt_salvar(ActionEvent event) {
        if (validarFormulario()) {
            String nome = campoNome.getText();
            String tombo = campoTombo.getText();
            String numeroSerie = campoNumeroSerie.getText();
            String categoria = campoCategoria.getText();
            String descricao = campoDescricao.getText();
            Localidade localidade = Localidade.valueOf(campoLocalidade.getText());
            Status status = comboStatus.getValue();

            ItemPatrimoniado patrimoniado = new ItemPatrimoniado(nome, tombo, numeroSerie, descricao, localidade, categoria, status, arquivoImagemSelecionado.getPath());
            patrimoniado.setEntrada(patrimoniadoClass.getEntrada());
            PatrimoniadoManager patrimoniadoManager = new PatrimoniadoManager();

            try {
                patrimoniadoManager.atualizar(patrimoniado);
                Alertas.informacao("Atualizado com sucesso");
                voltarDetalhes(patrimoniado, event);
            } catch (PatrimonioExistenteException e) {
                Alertas.aviso("Já existe um Patrimonio com esse tombo.");
            } catch (IllegalArgumentException e) {
                Alertas.aviso(e.getMessage());
            } catch (PersistenciaException e) {
                Alertas.erro("Não foi possível salvar. Tente novamente.");
            }

        }
    }

    @Override
    public void tranferiDados(Item item) {
        this.patrimoniadoClass = (ItemPatrimoniado) item;
        PatrimonioLoader.carregarDados(patrimoniadoClass, campoNome, campoTombo, campoNumeroSerie, campoCategoria, campoLocalidade,
                comboStatus, campoDescricao, null, null, imagemPatrimonio, labelSemFoto);
        arquivoImagemSelecionado = new File(patrimoniadoClass.getFoto());
    }
}
