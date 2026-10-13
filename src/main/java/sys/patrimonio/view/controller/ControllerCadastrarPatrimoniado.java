package sys.patrimonio.view.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;

import java.io.File;

public class ControllerCadastrarPatrimoniado {
    @FXML
    private ImageView imagemPatrimonio;

    @FXML
    private Label labelSemFoto;

    private File arquivoImagemSelecionado;

    @FXML
    private void btt_buscarFoto(ActionEvent event){
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
    private void btt_cancelar(ActionEvent event){

    }

    @FXML
    private void btt_salvar(ActionEvent event){

    }

}
