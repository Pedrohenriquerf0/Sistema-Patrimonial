package sys.patrimonio.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sys.patrimonio.model.Item;
import sys.patrimonio.view.controller.Alertas;
import sys.patrimonio.view.controller.DataBridge;

import java.io.InputStream;

public final class LoaderTelas {
    public static void loaderTela(Item item, ActionEvent event, DataBridge controller, String caminhoTela) { // EX: view/exemple.fxml
        try (InputStream fxmlStream = LoaderTelas.class.getClassLoader().getResourceAsStream(caminhoTela)) {
            if (fxmlStream == null) {
                throw new RuntimeException("ARQUIVO NÃO ENCONTRADO: verifique se o caminho " + caminhoTela + " está correto.");
            }
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = fxmlLoader.load(fxmlStream);
            controller = fxmlLoader.getController();
            controller.tranferiDados(item);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (Exception e) {
            Alertas.erro("ARQUIVO NÃO ENCONTRADO: verifique se o caminho " + caminhoTela + " está correto.");
        }
    }


    public static void loaderTela(Item item, MouseEvent event, DataBridge controller, String caminhoTela) { // EX: view/exemple.fxml
        try (InputStream fxmlStream = LoaderTelas.class.getClassLoader().getResourceAsStream(caminhoTela)) {
            if (fxmlStream == null) {
                throw new RuntimeException("ARQUIVO NÃO ENCONTRADO: verifique se o caminho " + caminhoTela + " está correto.");
            }
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = fxmlLoader.load(fxmlStream);
            controller = fxmlLoader.getController();
            controller.tranferiDados(item);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (Exception e) {
            Alertas.erro("ARQUIVO NÃO ENCONTRADO: verifique se o caminho " + caminhoTela + " está correto.");
        }
    }

    public static void loaderTela(ActionEvent event, String caminhoTela){
        try (InputStream fxmlStream = LoaderTelas.class.getClassLoader().getResourceAsStream(caminhoTela)) {
            if (fxmlStream == null) {
                throw new RuntimeException("ARQUIVO NÃO ENCONTRADO: verifique se o caminho " + caminhoTela + " está correto.");
            }
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = fxmlLoader.load(fxmlStream);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (Exception e) {
           Alertas.erro("ARQUIVO NÃO ENCONTRADO: verifique se o caminho " + caminhoTela + " está correto.");
        }
    }
}
