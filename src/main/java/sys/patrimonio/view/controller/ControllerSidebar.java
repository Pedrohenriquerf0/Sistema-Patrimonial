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
    private Button btt_movimentacoes;
    @FXML
    private Button btt_cautelas;
    @FXML
    private Button btt_configuracoes;
    @FXML
    private Button btt_Relatorios;

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

    @FXML
    private void cautelas(ActionEvent event){
        LoaderTelas.loaderTela(event, "view/Cautelas.fxml");
    }

    @FXML
    private void movimentacoes(ActionEvent event){
        LoaderTelas.loaderTela(event, "view/Movimentacoes.fxml");
    }

    @FXML
    private void relatorios(ActionEvent event){
        LoaderTelas.loaderTela(event, "view/Relatorios.fxml");
    }
    @FXML
    private void configuracoes(ActionEvent event){
        LoaderTelas.loaderTela(event, "view/configuracoes.fxml");
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

    public void ativarCautelas(){
        ativar(btt_cautelas);
    }

    public void ativarMovimentacoes(){
        ativar(btt_movimentacoes);
    }

    public void ativarConfiguracoes(){
        ativar(btt_configuracoes);
    }

    public void ativarRelatorios() {ativar(btt_Relatorios);
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
