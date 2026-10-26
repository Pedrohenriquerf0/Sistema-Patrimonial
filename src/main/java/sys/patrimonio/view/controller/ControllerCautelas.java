package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCautelas implements Initializable {
    @FXML
    private ControllerSidebar sidebarController;
    @FXML
    private VBox listaCautelas;

    @FXML
    public void pesquisaCautelas(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebarController.ativarCautelas();

        for (int i = 1; i <= 10; i++) {
            Label card = new Label("Cautela #" + i);
            listaCautelas.getChildren().add(card);
        }
    }
}
