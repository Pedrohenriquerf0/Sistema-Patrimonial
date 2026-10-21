package sys.patrimonio.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sys.patrimonio.model.Item;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.util.LoaderTelas;
import sys.patrimonio.util.PatrimonioLoader;

public class ControllerItemPatrimoniado implements DataBridge {
    private ItemPatrimoniado patrimoniado;
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoTombo;
    @FXML
    private TextField campoNumeroSerie;
    @FXML
    private TextField campoCategoria;
    @FXML
    private TextField campoLocalidade;
    @FXML
    private TextField campoStatus;
    @FXML
    private TextField campoDescricao;
    @FXML
    private TextField campoEntrada;
    @FXML
    private TextField campoQuantidade;

    @FXML
    private ImageView imagemPatrimonio;

    @FXML
    private Label labelSemFoto;


    @Override
    public void tranferiDados(Item item) {
        this.patrimoniado = (ItemPatrimoniado) item;
        PatrimonioLoader.carregarDados(patrimoniado, campoNome, campoTombo, campoNumeroSerie, campoCategoria, campoLocalidade,
                campoStatus, campoDescricao, campoQuantidade, campoEntrada, imagemPatrimonio, labelSemFoto);
    }


    @FXML
    private void btt_editar(ActionEvent event) {
        carregarTelaEditar(patrimoniado, event);
    }

    @FXML
    private void btt_cautela(ActionEvent event) {
        carregarTelaCautela(patrimoniado, event);
    }

    private void carregarTelaCautela(Item item, ActionEvent event) {
        ControllerCriarCautela controllerCriarCautela = new ControllerCriarCautela();
        LoaderTelas.loaderTela(item, event, controllerCriarCautela, "view/CriarCautela.fxml");
    }

    private void carregarTelaEditar(Item item, ActionEvent event) {
        ControllerEditarPatrimoniado controllerEditarPatrimoniado = new ControllerEditarPatrimoniado();
        LoaderTelas.loaderTela(item, event, controllerEditarPatrimoniado, "view/EditarPatrimoniado.fxml");
    }
}
