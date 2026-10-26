package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import sys.patrimonio.model.TipoMovimentacao;

public class ControllerCardMovimentacoes{
    @FXML
    private HBox cardItem;
    @FXML
    private ImageView iconsIO;
    @FXML
    private Label textoIO, textoIO2,textoQuantidade, textoConsumo;

    public void setDados(String consumo, String quantidade, TipoMovimentacao tipoMovimentacao){
       textoConsumo.setText(consumo);
       textoQuantidade.setText(quantidade);
       if(tipoMovimentacao == TipoMovimentacao.ENTRADA){
           cardItem.getStyleClass().setAll("card-item", "entrada");
           textoIO.setText("Entrada");
           textoIO2.setText("Entrada");
           carregarIcone("/icons/arrow-square-up-right.png");
       }else {
           cardItem.getStyleClass().setAll("card-item", "saida");
           textoIO.setText("Saida");
           textoIO2.setText("Saida");
           carregarIcone("/icons/arrow-square-down-left.png");
       }
    }

    private void carregarIcone(String caminho){
        var url = getClass().getResource(caminho);

        if(url == null){
            throw new IllegalArgumentException("Icone não encontrado " + caminho);
        }
        iconsIO.setImage(new Image(url.toExternalForm()));
    }

}
