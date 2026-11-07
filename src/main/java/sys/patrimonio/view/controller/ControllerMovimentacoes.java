package sys.patrimonio.view.controller;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sys.patrimonio.model.Cautela;
import sys.patrimonio.model.MovimentacaoItemConsumo;
import sys.patrimonio.service.MovimentacoesConsulta;
import sys.patrimonio.util.LoaderTelas;
import sys.patrimonio.util.Processo;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class ControllerMovimentacoes implements Initializable {
    private List<MovimentacaoItemConsumo> movimentacaoItemConsumoList;
    private final MovimentacoesConsulta consulta = new MovimentacoesConsulta();
    @FXML
    private ControllerSidebar sidebarController;
    @FXML
    private Label totalCautelasLabel, ultimaCautelaLabel, textoResultado;
    @FXML
    private TextField buscarIdField;
    @FXML
    private TableView<MovimentacaoItemConsumo> tabela_movimentacoes;
    @FXML
    private TableColumn<MovimentacaoItemConsumo, String> idColumn;
    @FXML
    private TableColumn<MovimentacaoItemConsumo, String> itemColumn;
    @FXML
    private TableColumn<MovimentacaoItemConsumo, String> localDestinoColumn;
    @FXML
    private TableColumn<MovimentacaoItemConsumo, String> tipoColumn;
    @FXML
    private TableColumn<MovimentacaoItemConsumo, Number> quantidadeColumn;
    @FXML
    private TableColumn<MovimentacaoItemConsumo, String> dataColumn;
    @FXML
    private TableColumn<MovimentacaoItemConsumo, String> emissorColumn;


    @FXML
    public void pesquisaMovimentacoes() {
        String pesquisa = buscarIdField.getText();

        tabela_movimentacoes.getItems().clear();

        if (pesquisa == null || pesquisa.isBlank()) {
            list5Last();
            return;
        }

        movimentacaoItemConsumoList = consulta.buscarPorID(Long.parseLong(pesquisa));
        textoResultado.setText(pesquisa);
        tabela_movimentacoes.getItems().setAll(movimentacaoItemConsumoList);
    }

    private void movimentacoesList5Last() {
        try {
            movimentacaoItemConsumoList = consulta.movimentacoesList5Last();
        } catch (SQLException e) {
            movimentacaoItemConsumoList = List.of();
            Alertas.erro("Erro ao buscar as ultimas inserções.");
        }
    }

    public void list5Last() {
        tabela_movimentacoes.getItems().clear();

        movimentacoesList5Last();

        if (movimentacaoItemConsumoList == null) {
            return;
        }

        textoResultado.setText("Últimas 5 Movimentações");
        tabela_movimentacoes.getItems().setAll(movimentacaoItemConsumoList);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebarController.ativarMovimentacoes();
        configurarColunas();
        list5Last();
        atualizaTotalData();

        UnaryOperator<TextFormatter.Change> filtroNumerico = change -> {
            String novoTexto = change.getControlNewText();

            if (novoTexto.matches("\\d{0,9}")) {
                return change;
            }
            return null;
        };
        buscarIdField.setTextFormatter(new TextFormatter<>(filtroNumerico));

        tabela_movimentacoes.setRowFactory(tv -> {
            TableRow<MovimentacaoItemConsumo> linha = new TableRow<>();

            linha.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2
                        && !linha.isEmpty()) {

                    MovimentacaoItemConsumo itemConsumo = linha.getItem();
                    abrirDetalhes(itemConsumo, event);
                }
            });

            return linha;
        });
    }


    private void abrirDetalhes(MovimentacaoItemConsumo movimentacaoItemConsumo, MouseEvent event) {
        ControllerMovimentacoesDetalhe controllerMovimentacoesDetalhe = new ControllerMovimentacoesDetalhe();
        LoaderTelas.loaderTela(movimentacaoItemConsumo, event,controllerMovimentacoesDetalhe, "view/MovimentacoesDetalhe.fxml");
    }


    private void configurarColunas() {
        idColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getId())));


        itemColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(
                        cell.getValue().getItemConsumo().getNome()
                )
        );


        tipoColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getTipoMovimentacao())));


        localDestinoColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(
                        String.valueOf(cell.getValue().getDestino())
                )
        );


        quantidadeColumn.setCellValueFactory(cell ->
                new ReadOnlyIntegerWrapper(
                        cell.getValue().getQuantidade()
                )
        );

        emissorColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(cell.getValue().getEmissor()));

        dataColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(cell.getValue().getData())
        );

        tipoColumn.setCellFactory(column -> new TableCell<MovimentacaoItemConsumo, String>() {

            @Override
            protected void updateItem(String tipo, boolean empty) {
                super.updateItem(tipo, empty);

                if (empty || tipo == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(tipo);

                    if (tipo.equalsIgnoreCase("SAIDA")) {
                        setStyle("-fx-text-fill: #E53935; -fx-font-weight: bold;");
                    } else if (tipo.equalsIgnoreCase("ENTRADA")) {
                        setStyle("-fx-text-fill: #2E7D32; -fx-font-weight: bold;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });
    }

    public void atualizaTotalData(){
        try {
            Map<String, String> resultado = consulta.listarTotalData();
            totalCautelasLabel.setText(resultado.get("total"));
            ultimaCautelaLabel.setText(resultado.get("ultima_data"));
        } catch (SQLException e) {
            Alertas.erro("Erro ao buscar total de Movimentações");
        }

    }
}
