package sys.patrimonio.view.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.service.PatrimoniadoManager;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCadastrarPatrimoniado implements Initializable {

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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Foto do Patrimônio");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imagens (*.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg")
        );

        File file = fileChooser.showOpenDialog(imagemPatrimonio.getScene().getWindow());

        if (file != null) {
            arquivoImagemSelecionado = file;
            Image image = new Image(file.toURI().toString());
            imagemPatrimonio.setImage(image);
            labelSemFoto.setVisible(false); // Esconde o texto de aviso
        }
    }

    @FXML
    private void btt_limpar(ActionEvent event) {
        campoNome.clear();
        campoTombo.clear();
        campoNumeroSerie.clear();
        campoDescricao.clear();
        campoCategoria.clear();
        comboLocalidade.getSelectionModel().clearSelection();
        comboStatus.getSelectionModel().clearSelection();
        imagemPatrimonio.setImage(null);
        labelSemFoto.setVisible(true);

        this.atualizarCombox();
    }

    @FXML
    private void btt_salvar(ActionEvent event) {
        // TODO VOU IMPLEMENTAR UMA VALIDAÇÃO ANTES DE ATRIBUIR OS VALORES
        String nome = campoNome.getText();
        String tombo = campoTombo.getText();
        String numeroSerie = campoNumeroSerie.getText();
        String categoria = campoCategoria.getText();
        String descricao = campoDescricao.getText();
        Localidade localidade = comboLocalidade.getValue();
        Status status = comboStatus.getValue();

        // TODO VOU IMPLEMENTAR UMA VALIDAÇÃO ANTES DE SALVAR
        ItemPatrimoniado patrimoniado = new ItemPatrimoniado(nome,tombo,numeroSerie,descricao,localidade,categoria,status,arquivoImagemSelecionado.getPath());
        PatrimoniadoManager patrimoniadoManager = new PatrimoniadoManager();
        patrimoniadoManager.salvar(patrimoniado);

        this.btt_limpar(event);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboLocalidade.getItems().setAll(Localidade.values());
        comboStatus.getItems().setAll(Status.values());

    }


    protected void atualizarCombox() {
        comboStatus.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Status item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(comboStatus.getPromptText());
                }
            }
        });

        comboLocalidade.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Localidade item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(comboLocalidade.getPromptText());
                }
            }
        });
    }
}
