package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import sys.patrimonio.model.MovimentacaoItemConsumo;
import sys.patrimonio.service.Movimentacoesconsulta;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerMovimentacoes implements Initializable {
    private List<MovimentacaoItemConsumo> movimentacaoList;
    @FXML
    private VBox listaMovimentacoes;
    @FXML
    private ControllerSidebar sidebarController;

    public void pesquisaMovimentacoes() {

    }

    private void movimentacoesList5Last() {
        try {
            movimentacaoList = new Movimentacoesconsulta().movimentacoesList5Last();
        } catch (SQLException e) {
            Alertas.erro("Erro ao buscar as ultimas inserções.");
        }
    }

    private void adicionarCard(MovimentacaoItemConsumo consumo) {
        URL caminho = getClass().getResource("/view/CardMovimentacoes.fxml");

        if (caminho == null) {
            throw new IllegalStateException(
                    "Não foi encontrado: /view/CardMovimentacoes.fxml"
            );
        }

        try {
            FXMLLoader loader = new FXMLLoader(caminho);
            Parent card = loader.load();

            ControllerCardMovimentacoes controller =
                    loader.getController();

            controller.setDados(
                    consumo.getItemConsumo().getNome() + " · " + consumo.getItemConsumo().getId(),
                    String.valueOf(consumo.getQuantidade()),
                    consumo.getTipoMovimentacao()
            );

            listaMovimentacoes.getChildren().add(card);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao carregar card da movimentação",
                    e
            );
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebarController.ativarMovimentacoes();
        movimentacoesList5Last();
        System.out.println(movimentacaoList);


        for (MovimentacaoItemConsumo consumo : movimentacaoList) {
            adicionarCard(consumo);
        }
    }
}
