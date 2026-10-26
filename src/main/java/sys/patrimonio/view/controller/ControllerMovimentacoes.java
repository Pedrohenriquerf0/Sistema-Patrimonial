package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import sys.patrimonio.model.MovimentacaoItemConsumo;
import sys.patrimonio.service.Movimentacoesconsulta;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class ControllerMovimentacoes implements Initializable {
    private List<MovimentacaoItemConsumo> movimentacaoList;
    @FXML
    private TextField text_pesquisa;
    @FXML
    private VBox listaMovimentacoes;
    @FXML
    private ControllerSidebar sidebarController;

    public void pesquisaMovimentacoes() {
        String pesquisa = text_pesquisa.getText();

        listaMovimentacoes.getChildren().clear();

        if (pesquisa == null || pesquisa.isBlank()
                || !pesquisa.matches("\\d+")) {
            list5Last();
            return;
        }

        movimentacaoList = new Movimentacoesconsulta(pesquisa).buscarPorID();
        for (MovimentacaoItemConsumo consumo : movimentacaoList) {
            adicionarCard(consumo);
        }
    }

    private void movimentacoesList5Last() {
        try {
            movimentacaoList =
                    new Movimentacoesconsulta().movimentacoesList5Last();
        } catch (SQLException e) {
            movimentacaoList = List.of();
            Alertas.erro("Erro ao buscar as últimas movimentações.");
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
        list5Last();


        UnaryOperator<TextFormatter.Change> filtroNumerico = change -> {
            String novoTexto = change.getControlNewText();

            if (novoTexto.matches("\\d{0,9}")) {
                return change;
            }
            return null;
        };

        text_pesquisa.setTextFormatter(new TextFormatter<>(filtroNumerico));
    }

    public void list5Last() {
        listaMovimentacoes.getChildren().clear();

        movimentacoesList5Last();

        if (movimentacaoList == null) {
            return;
        }

        for (MovimentacaoItemConsumo consumo : movimentacaoList) {
            adicionarCard(consumo);
        }
    }
}
