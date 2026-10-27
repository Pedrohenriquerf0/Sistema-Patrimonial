package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import sys.patrimonio.model.Cautela;
import sys.patrimonio.service.CautelasConsulta;
import sys.patrimonio.util.Processo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerCautelas implements Initializable {
    private List<Cautela> cautelaList;
    @FXML
    private ControllerSidebar sidebarController;
    @FXML
    private VBox listaCautelas;
    @FXML
    private TextField text_pesquisa;

    @FXML
    public void pesquisaCautelas() {
        String pesquisa = text_pesquisa.getText();

        listaCautelas.getChildren().clear();

        if (pesquisa == null || pesquisa.isBlank()) {
            list5Last();
            return;
        }

        cautelaList = new CautelasConsulta().buscarPorID(Processo.IDSomente(pesquisa));
        for (Cautela cautela : cautelaList) {
            adicionarCard(cautela);
        }
    }

    private void cautelaList5Last() {
        try {
            cautelaList = new CautelasConsulta().cautelaList5Last();
        } catch (SQLException e) {
            cautelaList = List.of();
            Alertas.erro("Erro ao buscar as ultimas inserções.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebarController.ativarCautelas();
        list5Last();

    }


    public void list5Last() {
        listaCautelas.getChildren().clear();

        cautelaList5Last();

        if (cautelaList == null) {
            return;
        }

        for (Cautela cautela : cautelaList) {
            adicionarCard(cautela);
        }
    }

    private void adicionarCard(Cautela cautela) {
        URL caminho = getClass().getResource("/view/CardCautela.fxml");

        if (caminho == null) {
            throw new IllegalStateException(
                    "Não foi encontrado: /view/CardMovimentacoes.fxml"
            );
        }

        try {
            FXMLLoader loader = new FXMLLoader(caminho);
            Parent card = loader.load();

            ControllerCardCautela controller =
                    loader.getController();

            controller.setDados(cautela);

            listaCautelas.getChildren().add(card);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao carregar card da movimentação",
                    e
            );
        }
    }
}
