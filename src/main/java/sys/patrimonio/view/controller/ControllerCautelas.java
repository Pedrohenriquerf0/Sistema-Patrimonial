package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCautelas implements Initializable {
    @FXML
    private ControllerSidebar sidebarController;

    @FXML
    public void pesquisaCautelas(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebarController.ativarCautelas();
    }
}
