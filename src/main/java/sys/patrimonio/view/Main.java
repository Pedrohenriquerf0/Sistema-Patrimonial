package sys.patrimonio.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;

public class Main extends Application {

    @Override
    public void start(Stage homeStage) {

        try (InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream("view/Home.fxml")) {

            if (fxmlStream == null) {
                throw new RuntimeException("ARQUIVO NÃO ENCONTRADO: verifique se o caminho view/Home.fxml está correto.");
            }

            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = fxmlLoader.load(fxmlStream);

            Scene scene = new Scene(parent);
            homeStage.setTitle("SISTEMA PATRIMONIAL");
            homeStage.setScene(scene);
            homeStage.setResizable(false);
            homeStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao iniciar a aplicação: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
