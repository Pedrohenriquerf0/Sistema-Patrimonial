package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;



import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCardCautelas implements Initializable {
    @FXML
    private HBox cardItem;
    @FXML
    private Image cardIO;
    @FXML
    private Label textoIO, textoIO2,textoQuantidade, textoPatrimonio;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cardItem.setStyle("card-item entrada");
    }

}
