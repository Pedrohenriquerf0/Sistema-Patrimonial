package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sys.patrimonio.model.Item;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerHome implements Initializable {
    @FXML
    private ControllerSidebar sidebarController;
    @FXML
    private TextField text_pesquisa;
    @FXML
    private TableView<Item> tabela_view;


    @FXML
    private void pesquisaPatrimonio(){

        String pesquisa = text_pesquisa.getText();
        if(!pesquisa.isBlank()){

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sidebarController.ativarHome();
    }
}
