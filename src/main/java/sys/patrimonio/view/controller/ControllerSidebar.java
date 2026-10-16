package sys.patrimonio.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.List;

public class ControllerSidebar {
    @FXML
    private Button btt_home;
    @FXML
    private Button btt_CadastrarPatrimoniado;
    @FXML
    private Button btt_CadastrarConsumo;

    @FXML
    private void voltarHome(ActionEvent event){
        try (InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream("view/Home.fxml")){
            if (fxmlStream == null) {
                throw new RuntimeException("ARQUIVO NÃO ENCONTRADO: verifique se o caminho view/Home.fxml está correto.");
            }
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = fxmlLoader.load(fxmlStream);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @FXML
    private void cadastrarPatrimoniado(ActionEvent event){
        try (InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream("view/CadastrarPatrimoniado.fxml")){
            if (fxmlStream == null) {
                throw new RuntimeException("ARQUIVO NÃO ENCONTRADO: verifique se o caminho view/Home.fxml está correto.");
            }
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = fxmlLoader.load(fxmlStream);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void cadastrarConsumo(ActionEvent event){
        try (InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream("view/CadastrarConsumo.fxml")){
            if (fxmlStream == null) {
                throw new RuntimeException("ARQUIVO NÃO ENCONTRADO: verifique se o caminho view/Home.fxml está correto.");
            }
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = fxmlLoader.load(fxmlStream);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ativarHome()               { ativar(btt_home); }
    public void ativarCadastroPatrimonio() { ativar(btt_CadastrarPatrimoniado); }
    public void ativarCadastroConsumo()    { ativar(btt_CadastrarConsumo); }

    private void ativar(Button ativo) {
        for (Button b : List.of(btt_home, btt_CadastrarPatrimoniado, btt_CadastrarConsumo)) {
            b.getStyleClass().remove("menu-button-active");
            if (!b.getStyleClass().contains("menu-button")) b.getStyleClass().add("menu-button");
        }
        ativo.getStyleClass().remove("menu-button");
        ativo.getStyleClass().add("menu-button-active");
    }

}
