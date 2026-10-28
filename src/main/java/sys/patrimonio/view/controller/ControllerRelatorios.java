package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sys.patrimonio.config.AppContext;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.repository.PatrimoniadoRepositorio;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerRelatorios implements Initializable {
    private PatrimoniadoRepositorio patrimoniadoRepositorio = AppContext.getPatrimoniadoRepositorio();
    @FXML
    private ControllerSidebar sidebarController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebarController.ativarRelatorios();
        Thread.ofVirtual().start(() -> {

            List<ItemPatrimoniado> patrimoniadoList = patrimoniadoRepositorio.listar();
            for (ItemPatrimoniado patrimoniado : patrimoniadoList) {
                System.out.println(patrimoniado.getNome() + "" + patrimoniado.getTombo());
            }
            System.out.println("thread Virtual");
        });
    }
}
