package sys.patrimonio.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sys.patrimonio.model.Cautela;
import sys.patrimonio.model.Item;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.service.CriarCautela;
import sys.patrimonio.util.ComboBoxHelper;
import sys.patrimonio.util.LoaderTelas;
import sys.patrimonio.util.PatrimonioLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerCriarCautela implements Initializable, DataBridgePatrimonio {
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
    private ComboBox<Localidade> comboLocalDestino;
    @FXML
    private TextField campoLocalOrigem;
    @FXML
    private TextField campoStatus;
    @FXML
    private TextField campoObservacao;
    @FXML
    private TextField campoEntrada;
    @FXML
    private TextField campoQuantidade;

    @FXML
    private ImageView imagemPatrimonio;

    @FXML
    private Label labelSemFoto;

    @FXML
    public void btt_canlelar(ActionEvent event) {
        boolean resposta = Alertas.confirmar("Deseja Cancelar?");
        if (resposta) {
            voltarDetalhes(patrimoniado, event);
        }
    }

    private void voltarDetalhes(Item item, ActionEvent event) {
        ControllerItemPatrimoniado controllerItemPatrimoniado = new ControllerItemPatrimoniado();
        LoaderTelas.loaderTela(item, event, controllerItemPatrimoniado, "view/ItemPatrimoniado.fxml");
    }

    @FXML
    public void btt_gerar(ActionEvent event) {
        try {
            if (validarFormulario()) {
                Localidade destino = comboLocalDestino.getValue();
                Localidade origem = Localidade.valueOf(campoLocalOrigem.getText());
                String observacao = campoObservacao.getText();
                CriarCautela cautela = CriarCautela.criarCautela(new Cautela(patrimoniado, destino, origem, observacao));
                Alertas.informacao("Cautela Criada com sucesso!!");
                cautela.visualizarCautela();
                voltarDetalhes(patrimoniado, event);
            }
        } catch (IOException e) {
            Alertas.erro("ERRO AO CRIAR CAUTELA.");
        }

    }

    private boolean validarFormulario() {
        List<String> faltando = new ArrayList<>();
        if (comboLocalDestino.getValue() == null) faltando.add("Localidade de destino");
        if (campoLocalOrigem.getText().equals(String.valueOf(comboLocalDestino.getValue())))
            faltando.add("Localidade de destino não pode ser igual a origem");

        if (!faltando.isEmpty()) {
            Alertas.aviso("Preencha: " + String.join(", ", faltando) + ".");
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComboBoxHelper.carregarLocalidade(comboLocalDestino);
    }

    @Override
    public void tranferiDados(Item item) {
        this.patrimoniado = (ItemPatrimoniado) item;
        PatrimonioLoader.carregarDados(patrimoniado, campoNome, campoTombo, campoNumeroSerie, campoCategoria, campoLocalOrigem,
                campoStatus, null, campoQuantidade, campoEntrada, imagemPatrimonio, labelSemFoto);
    }
}
