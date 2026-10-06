package sys.patrimonio.view;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sys.patrimonio.config.AppContext;
import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.exceptions.AppContextInicializacaoException;
import sys.patrimonio.repository.*;

import java.io.InputStream;

public class AppLauncher extends Application {
    @Override
    public void start(Stage homeStage) {

        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            PatrimoniadoRepositorio patrimoniadoRepositorio = new PatrimoniadoDAO(connectionFactory);
            ConsumoRepositorio consumoRepositorio = new ConsumoDAO(connectionFactory);
            CautelaRepositorio cautelaRepositorio = new CautelaDAO(connectionFactory);
            MovimentacaoRepositorio movimentacaoRepositorio = new MovimentacaoDAO(connectionFactory);

            AppContext.inicializar(cautelaRepositorio, movimentacaoRepositorio, consumoRepositorio, patrimoniadoRepositorio);

        } catch (RuntimeException e){
            throw new AppContextInicializacaoException("Falha ao conectar com o banco de dados", e);
        }


        try (InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream("view/Home.fxml")) {

            if (fxmlStream == null) {
                throw new RuntimeException("ARQUIVO NÃO ENCONTRADO: verifique se o caminho view/Home.fxml está correto.");  // TODO - criar exceptions para esse tipo
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
    public static void app(String[] args) {
        launch(args);
    }
}
