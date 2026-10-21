package sys.patrimonio.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sys.patrimonio.model.Item;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.util.PatrimonioLoader;

import java.io.File;
import java.io.InputStream;

public class ControllerItemConsumo {
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

    private File arquivoImagemSelecionado;

    @FXML
    public void btt_Movimentacao(ActionEvent event){
        //TODO IMPLEMENTAR MOVIMENTAÇÃO
    }

    @FXML
    public void btt_editar(ActionEvent event){
        carregarTelaEditar(consumo, event);
    }

    public void tranferiDados(ItemConsumo consumo) {
        this.consumo = consumo;
        PatrimonioLoader.carregarDados(consumo, campoNome, campoID, null, campoCategoria, campoLocalidade,
                campoStatus, campoDescricao, campoQuantidade, campoEntrada, imagemPatrimonio, labelSemFoto);
    }

    private void carregarTelaEditar(Item item, ActionEvent event) {
        if (item instanceof ItemConsumo consumo) {
            try (InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream("view/EditarConsumo.fxml")){
                if (fxmlStream == null) {
                    throw new RuntimeException("ARQUIVO NÃO ENCONTRADO: verifique se o caminho view/EditarConsumo.fxml está correto.");
                }
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent parent = fxmlLoader.load(fxmlStream);
                ControllerEditarConsumo controller = fxmlLoader.getController();
                controller.tranferiDados(consumo);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(parent));
                stage.show();

            } catch (Exception e) {
                Alertas.erro("ARQUIVO NÃO ENCONTRADO: verifique se o caminho view/EditarConsumo.fxml está correto.");
            }
        }
    }

}
