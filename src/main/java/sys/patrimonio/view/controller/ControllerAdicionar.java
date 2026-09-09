package sys.patrimonio.view.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.InputStream;

public class ControllerAdicionar {




    @FXML
    private void voltarHomer(ActionEvent event){
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
}
