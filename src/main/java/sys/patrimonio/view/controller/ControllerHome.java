package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sys.patrimonio.model.Item;



public class ControllerHome {

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


}
