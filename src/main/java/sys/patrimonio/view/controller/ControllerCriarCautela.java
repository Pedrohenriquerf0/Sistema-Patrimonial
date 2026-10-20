package sys.patrimonio.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sys.patrimonio.model.Cautela;
import sys.patrimonio.model.Item;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.service.CriarCautela;
import sys.patrimonio.util.ComboBoxHelper;
import sys.patrimonio.util.PatrimonioLoader;



import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerCriarCautela implements Initializable {
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
        if(resposta){
            voltarDetalhes(patrimoniado, event);
        }
    }

    private void voltarDetalhes(Item item, ActionEvent event) {
        if (item instanceof ItemPatrimoniado patrimonio) {
            try (InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream("view/ItemPatrimoniado.fxml")){
                if (fxmlStream == null) {
                    throw new RuntimeException("ARQUIVO NÃO ENCONTRADO: verifique se o caminho view/ItemPatrimoniado.fxml está correto.");
                }
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent parent = fxmlLoader.load(fxmlStream);
                ControllerItemPatrimoniado controller = fxmlLoader.getController();
                controller.tranferiDados(patrimonio);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(parent));
                stage.show();

            } catch (Exception e) {
                Alertas.erro("ARQUIVO NÃO ENCONTRADO: verifique se o caminho view/ItemPatrimoniado.fxml está correto.");
            }
        }
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
        } catch(IOException e){
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

    public void tranferiDados(ItemPatrimoniado patrimoniado) {
        this.patrimoniado = patrimoniado;
        PatrimonioLoader.carregarDados(patrimoniado, campoNome, campoTombo, campoNumeroSerie, campoCategoria, campoLocalOrigem,
                campoStatus, null, campoQuantidade, campoEntrada, imagemPatrimonio, labelSemFoto);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComboBoxHelper.carregarLocalidade(comboLocalDestino);
    }
}
