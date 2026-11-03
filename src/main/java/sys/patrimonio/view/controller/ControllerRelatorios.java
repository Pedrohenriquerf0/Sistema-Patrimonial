package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import sys.patrimonio.config.AppContext;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.repository.PatrimoniadoRepositorio;
import sys.patrimonio.util.ComboBoxHelper;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerRelatorios implements Initializable {
    private PatrimoniadoRepositorio patrimoniadoRepositorio = AppContext.getPatrimoniadoRepositorio();
    @FXML
    private ControllerSidebar sidebarController;
    @FXML
    private ComboBox<Status> comboStatus;
    @FXML
    private ComboBox<Localidade> comboLocalidade;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebarController.ativarRelatorios();
        ComboBoxHelper.carregarLocalidade(comboLocalidade);
        ComboBoxHelper.carregarStatus(comboStatus);
        Thread.ofVirtual().start(() -> {

            List<ItemPatrimoniado> patrimoniadoList = patrimoniadoRepositorio.listar();
            for (ItemPatrimoniado patrimoniado : patrimoniadoList) {
                System.out.println(patrimoniado.getNome() + "" + patrimoniado.getTombo());
            }
            System.out.println("thread Virtual");
        });
    }

    public void limparFiltros() {
        comboLocalidade.getSelectionModel().clearSelection();
        comboStatus.getSelectionModel().clearSelection();
        ComboBoxHelper.configurarCelulasComboBox(comboLocalidade, comboStatus);
    }

    public void gerarRelatorio() {

    }
}
