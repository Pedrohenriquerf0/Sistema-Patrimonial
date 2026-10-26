package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import sys.patrimonio.model.Cautela;
import sys.patrimonio.service.CautelasConsulta;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerCautelas implements Initializable {
    @FXML
    private ControllerSidebar sidebarController;
    @FXML
    private VBox listaCautelas;

    @FXML
    public void pesquisaCautelas(){

    }

    private List<Cautela> cautelaList5Last(){
        List<Cautela> cautelaList = List.of();
        try {
           cautelaList = new CautelasConsulta().cautelaList5Last();
        } catch (SQLException e) {
            Alertas.erro("Erro ao buscar as ultimas inserções.");
        }
        return cautelaList;
    }

    private void mostraTela(){
       // listaCautelas.setId();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebarController.ativarCautelas();
        List<Cautela> cautela5Last = cautelaList5Last();

        for(Cautela cautela: cautela5Last){
            //listaCautelas.getChildren().add(cautela);
        }
        for (int i = 1; i <= 10; i++) {
            Label card = new Label("Cautela #" + i);
            listaCautelas.getChildren().add(card);
        }
    }
}
