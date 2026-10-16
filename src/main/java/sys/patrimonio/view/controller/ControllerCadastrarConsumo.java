package sys.patrimonio.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import sys.patrimonio.exceptions.PatrimonioExistenteException;
import sys.patrimonio.exceptions.PersistenciaException;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.service.ConsumoManager;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class ControllerCadastrarConsumo implements Initializable {

    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoCategoria;
    @FXML
    private TextField campoQuantidade;
    @FXML
    private ComboBox<Localidade> comboLocalidade;
    @FXML
    private ComboBox<Status> comboStatus;
    @FXML
    private TextArea campoDescricao;

    @FXML
    private ImageView imagemPatrimonio;

    @FXML
    private Label labelSemFoto;

    private File arquivoImagemSelecionado;

    @FXML
    private void btt_buscarFoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Foto do Patrimônio");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imagens (*.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg")
        );

        File file = fileChooser.showOpenDialog(imagemPatrimonio.getScene().getWindow());

        if (file != null) {
            arquivoImagemSelecionado = file;
            Image image = new Image(file.toURI().toString(), 272, 272, true, true);
            imagemPatrimonio.setImage(image);
            labelSemFoto.setVisible(false); // Esconde o texto de aviso
        }
    }

    @FXML
    private void btt_limpar(ActionEvent event) {
        if (Alertas.confirmar("Deseja Limpar?")) {
            limpar();
        }
    }

    private void limpar() {
        campoNome.clear();
        campoQuantidade.clear();
        campoDescricao.clear();
        campoCategoria.clear();
        comboLocalidade.getSelectionModel().clearSelection();
        comboStatus.getSelectionModel().clearSelection();
        imagemPatrimonio.setImage(null);
        labelSemFoto.setVisible(true);

        this.atualizarCombox();
    }

    private boolean validarFormulario() {
        List<String> faltando = new ArrayList<>();
        String quantidade = campoQuantidade.getText();

        if (campoNome.getText().isBlank()) faltando.add("Nome");
        if (quantidade.isBlank() || Integer.parseInt(quantidade) <= 0) faltando.add("quantidade");
        if (campoCategoria.getText().isBlank()) faltando.add("Categoria");
        if (comboLocalidade.getValue() == null) faltando.add("Localidade");
        if (comboStatus.getValue() == null) faltando.add("Status");
        if (arquivoImagemSelecionado == null) faltando.add("Foto");
        if (campoDescricao.getText() == null) faltando.add("Descrição");

        if (!faltando.isEmpty()) {
            Alertas.aviso("Preencha: " + String.join(", ", faltando) + ".");
            return false;
        }
        return true;
    }

    @FXML
    private void btt_salvar(ActionEvent event) {
        if (validarFormulario()) {
            String nome = campoNome.getText();
            int quantidade = Integer.parseInt(campoQuantidade.getText());
            String categoria = campoCategoria.getText();
            String descricao = campoDescricao.getText();
            Localidade localidade = comboLocalidade.getValue();
            Status status = comboStatus.getValue();

            ItemConsumo consumo = new ItemConsumo(nome, quantidade, descricao, localidade, categoria, status, arquivoImagemSelecionado.getPath());
            ConsumoManager consumoManager = new ConsumoManager();

            try {
                consumoManager.salvar(consumo);
                Alertas.informacao("Salvo com Sucesso");
                limpar();
            } catch (PatrimonioExistenteException e) {
                Alertas.aviso("Já existe um Patrimonio com esse tombo.");
            } catch (IllegalArgumentException e) {
                Alertas.aviso(e.getMessage());
            } catch (PersistenciaException e) {
                e.printStackTrace();
                Alertas.erro("Não foi possível salvar. Tente novamente.");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        UnaryOperator<TextFormatter.Change> filtroNumerico = change -> {
            String novoTexto = change.getControlNewText();

            // Expressão regular: "\\d*" significa "zero ou mais dígitos de 0 a 9"
            if (novoTexto.matches("\\d*")) {
                return change; // Permite a alteração
            }
            return null; // Rejeita a digitação! O caractere nem entra no campo
        };
        // Aplica o filtro no seu campo (ex: número de patrimônio ou quantidade)
        campoQuantidade.setTextFormatter(new TextFormatter<>(filtroNumerico));

        comboLocalidade.getItems().setAll(Localidade.values());
        comboStatus.getItems().setAll(Status.values());

    }


    protected void atualizarCombox() {
        comboStatus.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Status item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(comboStatus.getPromptText());
                }
            }
        });

        comboLocalidade.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Localidade item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(comboLocalidade.getPromptText());
                }
            }
        });
    }
}
