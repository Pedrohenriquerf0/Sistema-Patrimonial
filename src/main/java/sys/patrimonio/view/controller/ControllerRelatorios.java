package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRelatorios implements Initializable {
    @FXML
    private ControllerSidebar sidebarController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebarController.ativarRelatorios();
    }
}
