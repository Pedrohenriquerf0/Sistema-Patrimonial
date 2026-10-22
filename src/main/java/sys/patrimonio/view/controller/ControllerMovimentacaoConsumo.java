package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sys.patrimonio.model.Item;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.TipoMovimentacao;
import sys.patrimonio.util.ComboBoxHelper;
import sys.patrimonio.util.PatrimonioLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMovimentacaoConsumo implements Initializable, DataBridge {
    private ItemConsumo consumo;
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoID;
    @FXML
    private TextField campoNumeroSerie;
    @FXML
    private TextField campoCategoria;
    @FXML
    private ComboBox<Localidade> comboLocalDestino;
    @FXML
    private TextField campoLocalidade;
    @FXML
    private TextField campoStatus;
    @FXML
    private TextField campoEntrada;
    @FXML
    private TextField campoQuantidadeAtual;
    @FXML
    private TextField campoQuantidadeMovimentacao;
    @FXML
    private ComboBox<TipoMovimentacao> comboTipoMovimentacao;
    @FXML
    private ImageView imagemPatrimonio;

    @FXML
    private Label labelSemFoto;

    public void btt_canlelar(){

    }

    public void btt_salvar(){

    }

    @Override
    public void tranferiDados(Item item) {
        this.consumo = (ItemConsumo) item;
        PatrimonioLoader.carregarDados(consumo, campoNome, campoID, null, campoCategoria, campoLocalidade,
                campoStatus, null, campoQuantidadeAtual, campoEntrada, imagemPatrimonio, labelSemFoto);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComboBoxHelper.carregarLocalidade(comboLocalDestino);
        ComboBoxHelper.carregarTipoMovimentacao(comboTipoMovimentacao);
    }
}
