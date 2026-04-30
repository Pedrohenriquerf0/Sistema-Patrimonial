package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sys.patrimonio.model.ItemPatrimonio;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.service.ItemManager;

import javax.swing.*;
import java.io.InputStream;


public class ControllerHomer {

    @FXML
    private TextField text_pesquisa;
    @FXML
    private TableView<ItemPatrimonio> tabela_view;


    @FXML
    private void pesquisaPatrimonio(){

        String pesquisa = text_pesquisa.getText();
        ItemPatrimonio pc = new ItemPatrimonio("PC", "AADC", "ASQW321546", "PC GAMER", Localidade.TI, "INFORMATICA", "src/main/resources/teste.png");
        ItemManager itemManager = new ItemManager();
        itemManager.adcionarItem(pc);


        if(!pesquisa.isBlank()){

        }
    }

    @FXML
    private void adicionarPatrimonio(ActionEvent event){
        try (InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream("view/Adicionar.fxml")){
            if (fxmlStream == null) {
                throw new RuntimeException("ARQUIVO NÃO ENCONTRADO: verifique se o caminho view/Homer.fxml está correto.");
            }
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = fxmlLoader.load(fxmlStream);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
