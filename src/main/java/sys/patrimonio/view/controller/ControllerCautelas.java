package sys.patrimonio.view.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sys.patrimonio.model.Cautela;
import sys.patrimonio.service.CautelasConsulta;
import sys.patrimonio.util.Processo;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ControllerCautelas implements Initializable {
    private CautelasConsulta cautelasConsulta = new CautelasConsulta();
    private List<Cautela> cautelaList;
    @FXML
    private ControllerSidebar sidebarController;
    @FXML
    private Label totalCautelasLabel, ultimaCautelaLabel, textoResultado;
    @FXML
    private TextField buscarIdField;
    @FXML
    private TableView<Cautela> tabela_cautelas;
    @FXML
    private TableColumn<Cautela, String> idColumn;
    @FXML
    private TableColumn<Cautela, String> itemColumn;
    @FXML
    private TableColumn<Cautela, String> localOrigemColumn;
    @FXML
    private TableColumn<Cautela, String> localDestinoColumn;
    @FXML
    private TableColumn<Cautela, String> tomboColumn;
    @FXML
    private TableColumn<Cautela, String> dataColumn;


    @FXML
    public void pesquisaCautelas() {
        String pesquisa = buscarIdField.getText();

        tabela_cautelas.getItems().clear();

        if (pesquisa == null || pesquisa.isBlank()) {
            list5Last();
            return;
        }

        cautelaList = cautelasConsulta.buscarPorID(Processo.IDSomente(pesquisa));
        textoResultado.setText(pesquisa);
        tabela_cautelas.getItems().setAll(cautelaList);
    }

    private void cautelaList5Last() {

        try {
            cautelaList = cautelasConsulta.cautelaList5Last();
        } catch (SQLException e) {
            cautelaList = List.of();
            Alertas.erro("Erro ao buscar as ultimas inserções.");
        }
    }

    public void list5Last() {
        tabela_cautelas.getItems().clear();

        cautelaList5Last();

        if (cautelaList == null) {
            return;
        }

        textoResultado.setText("Últimas 5 Cautelas");
        tabela_cautelas.getItems().setAll(cautelaList);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebarController.ativarCautelas();
        configurarColunas();
        list5Last();
        atualizaTotalData();

        tabela_cautelas.setRowFactory(tv -> {
            TableRow<Cautela> linha = new TableRow<>();

            linha.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2
                        && !linha.isEmpty()) {

                    Cautela cautela = linha.getItem();
                    abrirDetalhes(cautela, event);
                }
            });

            return linha;
        });
    }


    private void abrirDetalhes(Cautela cautela, MouseEvent event) {
       // TODO ABRI DETALHE
    }


    private void configurarColunas() {
        idColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(cell.getValue().getId()));


        itemColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(
                        cell.getValue().getItemPatrimoniado().getNome()
                )
        );


        localOrigemColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getOrigem()))
        );


        localDestinoColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(
                        String.valueOf(cell.getValue().getDestino())
                )
        );


        tomboColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(cell.getValue().getItemPatrimoniado().getTombo())
        );

        dataColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(cell.getValue().getData())
        );

    }

    public void atualizaTotalData(){
        try {
            Map<String, String> resultado = cautelasConsulta.listarTotalData();
            totalCautelasLabel.setText(resultado.get("total"));
            ultimaCautelaLabel.setText(resultado.get("ultima_data"));
        } catch (SQLException e) {
            Alertas.erro("Erro ao buscar total de cautelas");
        }

    }
}
