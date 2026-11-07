package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sys.patrimonio.model.Cautela;
import sys.patrimonio.model.EntidadeModel;
import sys.patrimonio.service.CriarCautela;
import sys.patrimonio.util.PatrimonioLoader;

import java.io.IOException;

public class ControllerCautelaDetalhe implements DataBridgeOperacoes {
    private Cautela cautela;
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoTombo;
    @FXML
    private TextField campoNumeroSerie;
    @FXML
    private TextField campoCategoria;
    @FXML
    private TextField campoLocalDestino;
    @FXML
    private TextField campoLocalOrigem;
    @FXML
    private TextField campoStatus;
    @FXML
    private TextField campoObservacao;
    @FXML
    private TextField campoEntrada;
    @FXML
    private TextField campoDataCautela;
    @FXML
    private TextField campoEmissor;
    @FXML
    private TextField campoIDCautela;
    @FXML
    private TextField campoQuantidade;

    @FXML
    private ImageView imagemPatrimonio;

    @FXML
    private Label labelSemFoto;


    public void btt_visualizar(){
        try {
            CriarCautela cautelabd = CriarCautela.cautelaBD(cautela);
            Alertas.informacao("Cautela Criada com sucesso!!");
            cautelabd.visualizarCautela();
        }catch (IOException e) {
            Alertas.erro("ERRO AO VISUALIZAR CAUTELA.");
        }

    }


    @Override
    public void tranferiDados(EntidadeModel obj) {
        this.cautela = (Cautela) obj;
        PatrimonioLoader.carregarDadosCautela(cautela, campoNome, campoTombo, campoNumeroSerie,campoCategoria,campoLocalOrigem, campoLocalDestino,
                campoStatus,campoQuantidade,campoEntrada,
                campoDataCautela, campoEmissor, campoIDCautela, campoObservacao,imagemPatrimonio,labelSemFoto);

    }
}
