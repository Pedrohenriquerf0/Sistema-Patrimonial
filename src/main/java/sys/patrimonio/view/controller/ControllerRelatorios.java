package sys.patrimonio.view.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.service.RelatoriosManager;
import sys.patrimonio.util.ComboBoxHelper;
import sys.patrimonio.util.CriarXlsx;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

public class ControllerRelatorios implements Initializable {
    @FXML
    private ControllerSidebar sidebarController;
    @FXML
    private ComboBox<Status> comboStatus;
    @FXML
    private ComboBox<Localidade> comboLocalidade;
    @FXML
    private Label totalPatrimonios, patrimonios, consumos;
    @FXML
    private DatePicker dataInicial, dataFinal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebarController.ativarRelatorios();
        ComboBoxHelper.carregarLocalidade(comboLocalidade);
        ComboBoxHelper.carregarStatus(comboStatus);

        Thread.ofVirtual().name("THREAD INITIALIZE").start(() -> {
            RelatoriosManager relatoriosManager = new RelatoriosManager();

            try {
                Map<String, Integer> totais = relatoriosManager.totais();
                Platform.runLater(() -> {
                    totalPatrimonios.setText(Integer.toString(totais.get("patrimoniado") + totais.get("consumo")));
                    patrimonios.setText(Integer.toString(totais.get("patrimoniado")));
                    consumos.setText(String.valueOf(totais.get("consumo")));
                });
            } catch (SQLException e) {
                Platform.runLater(() -> {
                    Alertas.erro("Erro ao iniciar.....");
                });
            }
        });
    }

    public void limparFiltros() {
        dataInicial.editorProperty().get().clear();
        dataFinal.editorProperty().get().clear();
        comboLocalidade.getSelectionModel().clearSelection();
        comboStatus.getSelectionModel().clearSelection();
        ComboBoxHelper.configurarCelulasComboBox(comboLocalidade, comboStatus);
    }

    public void gerarRelatorio() {

        LocalDate data_inicial = dataInicial.getValue();
        LocalDate data_final = dataFinal.getValue();
        Status status = comboStatus.getValue();
        Localidade localidade = comboLocalidade.getValue();

        Thread.ofVirtual().name("THREAD GERAR RELATORIO").start(() -> {
            RelatoriosManager relatoriosManager = new RelatoriosManager(data_inicial, data_final, status, localidade);
            try {
                CriarXlsx criarXlsx = new CriarXlsx();
                String nomeArquivo = criarXlsx.criarXlsx(relatoriosManager.gerarRelatorio());
                Platform.runLater(() -> {
                    if (nomeArquivo == null) {
                        Alertas.aviso("Não foi possível criar o relatório, os filtros colocados não contêm dados.");
                    } else {
                        Alertas.informacao("Relatorio Criado com sucesso, planilha: " + nomeArquivo);
                    }
                });

            } catch (SQLException | IOException e) {
                Platform.runLater(() -> {
                    Alertas.erro("Erro ao fazer o relatorio.");
                });
            }
        });
    }
}
