package sys.patrimonio.view.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class Alertas {
    public static void erro(String mensagem){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void aviso(String mensagem){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static boolean confimar(String mensagem){
        ButtonType sim = new ButtonType("Sim", ButtonBar.ButtonData.OK_DONE);
        ButtonType nao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, mensagem, sim, nao);
        alerta.setHeaderText(null);

        return alerta.showAndWait().filter(botao -> botao == sim).isPresent();
    }
}
