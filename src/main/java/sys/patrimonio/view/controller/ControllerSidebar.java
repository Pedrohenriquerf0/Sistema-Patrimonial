package sys.patrimonio.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sys.patrimonio.util.LoaderTelas;

import java.util.List;

public class ControllerSidebar {
    @FXML
    private Button btt_home;
    @FXML
    private Button btt_CadastrarPatrimoniado;
    @FXML
    private Button btt_CadastrarConsumo;

    @FXML
    private void voltarHome(ActionEvent event) {
        LoaderTelas.loaderTela(event, "view/Home.fxml");
    }


    @FXML
    private void cadastrarPatrimoniado(ActionEvent event) {
        LoaderTelas.loaderTela(event, "view/CadastrarPatrimoniado.fxml");
    }

    @FXML
    private void cadastrarConsumo(ActionEvent event) {
        LoaderTelas.loaderTela(event, "view/CadastrarConsumo.fxml");
    }

    public void ativarHome() {
        ativar(btt_home);
    }

    public void ativarCadastroPatrimonio() {
        ativar(btt_CadastrarPatrimoniado);
    }

    public void ativarCadastroConsumo() {
        ativar(btt_CadastrarConsumo);
    }

    private void ativar(Button ativo) {
        for (Button b : List.of(btt_home, btt_CadastrarPatrimoniado, btt_CadastrarConsumo)) {
            b.getStyleClass().remove("menu-button-active");
            if (!b.getStyleClass().contains("menu-button")) b.getStyleClass().add("menu-button");
        }
        ativo.getStyleClass().remove("menu-button");
        ativo.getStyleClass().add("menu-button-active");
    }

}
