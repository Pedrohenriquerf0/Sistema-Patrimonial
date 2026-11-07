package sys.patrimonio.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sys.patrimonio.model.EntidadeModel;
import sys.patrimonio.model.Item;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.util.LoaderTelas;
import sys.patrimonio.util.PatrimonioLoader;

public class ControllerItemConsumo implements DataBridgePatrimonio {
    private ItemConsumo consumo;
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoCategoria;
    @FXML
    private TextField campoQuantidade;
    @FXML
    private TextField campoID;
    @FXML
    private TextField campoLocalidade;
    @FXML
    private TextField campoStatus;
    @FXML
    private TextField campoEntrada;
    @FXML
    private TextField campoDescricao;

    @FXML
    private ImageView imagemPatrimonio;

    @FXML
    private Label labelSemFoto;


    @FXML
    public void btt_Movimentacao(ActionEvent event) {
        ControllerCriarMovimentacaoConsumo controllerMovimentacaoConsumo = new ControllerCriarMovimentacaoConsumo();
        LoaderTelas.loaderTela(consumo, event, controllerMovimentacaoConsumo, "view/CriarMovimentacaoConsumo.fxml");
    }

    @FXML
    public void btt_editar(ActionEvent event) {
        carregarTelaEditar(consumo, event);
    }

    @Override
    public void tranferiDados(EntidadeModel model) {
        this.consumo = (ItemConsumo) model;
        PatrimonioLoader.carregarDados(consumo, campoNome, campoID, null, campoCategoria, campoLocalidade,
                campoStatus, campoDescricao, campoQuantidade, campoEntrada, imagemPatrimonio, labelSemFoto);
    }

    private void carregarTelaEditar(Item item, ActionEvent event) {
        ControllerEditarConsumo controllerEditarConsumo = new ControllerEditarConsumo();
        LoaderTelas.loaderTela(item,event, controllerEditarConsumo,"view/EditarConsumo.fxml");
    }


}
