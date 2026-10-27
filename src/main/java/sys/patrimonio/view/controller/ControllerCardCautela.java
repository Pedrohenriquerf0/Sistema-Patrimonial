package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import sys.patrimonio.model.Cautela;

public class ControllerCardCautela {
    @FXML
    private HBox cardItem;
    @FXML
    private ImageView iconsCautela;
    @FXML
    private Label textoOrigemDestino, textoData, textoPatrimonio, textoIDCautela;

    public void setDados(Cautela  cautela) {
        textoPatrimonio.setText(cautela.getItemPatrimoniado().getNome() + " · " + cautela.getItemPatrimoniado().getTombo());
        textoData.setText("Data: " + cautela.getData());
        textoIDCautela.setText("ID: " + cautela.getId());
        textoOrigemDestino.setText(cautela.getOrigem() + " -> " + cautela.getDestino());
        cardItem.getStyleClass().setAll("card-item", "entrada");
        carregarIcone("/icons/doc.png");

    }

    private void carregarIcone(String caminho) {
        var url = getClass().getResource(caminho);

        if (url == null) {
            throw new IllegalArgumentException("Icone não encontrado " + caminho);
        }
        iconsCautela.setImage(new Image(url.toExternalForm()));
    }
}
