package sys.patrimonio.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import sys.patrimonio.exceptions.SaldoInsuficienteException;
import sys.patrimonio.model.Item;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.TipoMovimentacao;
import sys.patrimonio.service.MovimentacaoConsumoManager;
import sys.patrimonio.util.ComboBoxHelper;
import sys.patrimonio.util.LoaderTelas;
import sys.patrimonio.util.PatrimonioLoader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class ControllerMovimentacaoConsumo implements Initializable, DataBridgePatrimonio {
    private ItemConsumo consumo;
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoID;
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


    public void btt_cancelar(ActionEvent event) {
        boolean resposta = Alertas.confirmar("Deseja Cancelar?");
        if (resposta) {
            voltarDetalhes(consumo, event);
        }
    }

    private void voltarDetalhes(Item item, ActionEvent event) {
        ControllerItemConsumo controllerItemConsumo = new ControllerItemConsumo();
        LoaderTelas.loaderTela(item, event, controllerItemConsumo, "view/ItemConsumo.fxml");
    }

    public void btt_salvar(ActionEvent event) {
        try {
            if (validarFormulario()) {

                int quantidadeMovimentacao = Integer.parseInt(campoQuantidadeMovimentacao.getText());

                MovimentacaoConsumoManager movimentacaoConsumoManager = new MovimentacaoConsumoManager();
                if (comboTipoMovimentacao.getValue().equals(TipoMovimentacao.ENTRADA)) {
                    movimentacaoConsumoManager.registrarEntrada(consumo, quantidadeMovimentacao);
                } else {
                    Localidade destino = comboLocalDestino.getValue();
                    movimentacaoConsumoManager.registrarSaida(consumo, quantidadeMovimentacao, destino);
                }
                Alertas.informacao("Movimentação gerada com sucesso!!!");
                voltarDetalhes(consumo, event);
            }
        } catch (SaldoInsuficienteException | IllegalArgumentException e) {
            Alertas.erro("Erro ao gerar movimentação!!");
        }
    }

    private boolean validarFormulario() {
        List<String> faltando = new ArrayList<>();
        Integer quantidadeAtual = Integer.parseInt(campoQuantidadeAtual.getText());
        Integer quantidadeMovimentacao = Integer.parseInt(campoQuantidadeMovimentacao.getText());
        if (comboTipoMovimentacao.getValue() != TipoMovimentacao.ENTRADA && comboLocalDestino.getValue() == null) faltando.add("Localidade de destino");
        if (comboTipoMovimentacao.getValue() == null) faltando.add("Tipo de Movimentação");
        if (comboTipoMovimentacao.getValue() == null || comboTipoMovimentacao.getValue().equals(TipoMovimentacao.SAIDA) && quantidadeAtual < quantidadeMovimentacao)
            faltando.add("Quantidade tem que ser menor que o estoque atual");
        if (comboTipoMovimentacao.getValue() == null || comboTipoMovimentacao.getValue().equals(TipoMovimentacao.ENTRADA) && quantidadeMovimentacao <= 0)
            faltando.add("Quantidade tem que ser maior que 0");
        if (campoLocalidade.getText().equals(String.valueOf(comboLocalDestino.getValue())))
            faltando.add("Localidade de destino não pode ser igual a origem");

        if (!faltando.isEmpty()) {
            Alertas.aviso("Preencha: " + String.join(", ", faltando) + ".");
            return false;
        }
        return true;
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

        UnaryOperator<TextFormatter.Change> filtroNumerico = change -> {
            String novoTexto = change.getControlNewText();

            // Expressão regular: "\\d*" significa "zero ou mais dígitos de 0 a 9"
            if (novoTexto.matches("\\d{0,9}")) {
                return change; // Permite a alteração
            }
            return null; // Rejeita a digitação! O caractere nem entra no    campo
        };
        // Aplica o filtro no seu campo (ex: número de patrimônio ou quantidade)
        campoQuantidadeMovimentacao.setTextFormatter(new TextFormatter<>(filtroNumerico));
    }
}
