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
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.util.PatrimonioLoader;
import java.io.InputStream;

public class ControllerItemPatrimoniado {
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


    public void tranferiDados(ItemPatrimoniado patrimoniado) {
        this.patrimoniado = patrimoniado;
        PatrimonioLoader.carregarDados(patrimoniado, campoNome, campoTombo, campoNumeroSerie, campoCategoria, campoLocalidade,
                campoStatus, campoDescricao, campoQuantidade, campoEntrada, imagemPatrimonio, labelSemFoto);
    }


    @FXML
    private void btt_editar(ActionEvent event){
        carregarTelaEditar(patrimoniado, event);
    }

    @FXML
    private void btt_cautela(ActionEvent event){
        carregarTelaCautela(patrimoniado,  event);
    }

    private void carregarTelaCautela(Item item, ActionEvent event) {
        if (item instanceof ItemPatrimoniado patrimonio) {
            try (InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream("view/CriarCautela.fxml")){
                if (fxmlStream == null) {
                    throw new RuntimeException("ARQUIVO NÃO ENCONTRADO: verifique se o caminho view/CriarCautela.fxml está correto.");
                }
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent parent = fxmlLoader.load(fxmlStream);
                ControllerCriarCautela controller = fxmlLoader.getController();
                controller.tranferiDados(patrimonio);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(parent));
                stage.show();

            } catch (Exception e) {
               Alertas.erro("ARQUIVO NÃO ENCONTRADO: verifique se o caminho view/CriarCautela.fxml está correto.");
            }
        }
    }

    private void carregarTelaEditar(Item item, ActionEvent event) {
        if (item instanceof ItemPatrimoniado patrimonio) {
            try (InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream("view/EditarPatrimoniado.fxml")){
                if (fxmlStream == null) {
                    throw new RuntimeException("ARQUIVO NÃO ENCONTRADO: verifique se o caminho view/EditarPatrimoniado.fxml está correto.");
                }
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent parent = fxmlLoader.load(fxmlStream);
                ControllerEditarPatrimoniado controller = fxmlLoader.getController();
                controller.tranferiDados(patrimonio);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(parent));
                stage.show();

            } catch (Exception e) {
               Alertas.erro("ARQUIVO NÃO ENCONTRADO: verifique se o caminho view/EditarPatrimoniado.fxml está correto.");
            }
        }
    }
}
