package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sys.patrimonio.model.EntidadeModel;
import sys.patrimonio.model.MovimentacaoItemConsumo;
import sys.patrimonio.util.PatrimonioLoader;

public class ControllerMovimentacoesDetalhe implements DataBridgeOperacoes {
    private MovimentacaoItemConsumo movimentacao;
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoID;
    @FXML
    private TextField campoTipo;
    @FXML
    private TextField campoCategoria;
    @FXML
    private TextField campoLocalDestino;
    @FXML
    private TextField campoEntrada;
    @FXML
    private TextField campoDataMovimentação;
    @FXML
    private TextField campoEmissor;
    @FXML
    private TextField campoIDMov;
    @FXML
    private TextField campoQuantidadeMovimentacao;

    @FXML
    private ImageView imagemPatrimonio;

    @FXML
    private Label labelSemFoto;


    @Override
    public void tranferiDados(EntidadeModel obj) {
        this.movimentacao = (MovimentacaoItemConsumo) obj;
        PatrimonioLoader.carregarDadosMov(movimentacao, campoNome, campoID, campoCategoria,
                campoLocalDestino, campoQuantidadeMovimentacao, campoEntrada,
                campoDataMovimentação, campoEmissor, campoIDMov, campoTipo, imagemPatrimonio, labelSemFoto);
    }
}
