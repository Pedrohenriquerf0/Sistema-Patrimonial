package sys.patrimonio.util;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sys.patrimonio.model.Item;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.model.Status;

import java.io.File;

public final class PatrimonioLoader {


    public static void carregarDados(
            Item item,
            TextField campoNome,
            TextField campoIdentificador,
            TextField campoNumeroSerie,
            TextField campoCategoria,
            TextField campoLocalOrigem,
            TextField campoStatus,
            TextField campoDescricao,
            TextField campoQuantidade,
            TextField campoEntrada,
            ImageView imagemPatrimonio,
            Label labelSemFoto
    ) {
        campoNome.setText(item.getNome());
        if (campoNumeroSerie != null && item instanceof ItemPatrimoniado patrimoniado) {
            campoNumeroSerie.setText(patrimoniado.getNumeroSerie());
        }

        if (campoIdentificador != null && item instanceof ItemPatrimoniado patrimoniado) {
            campoIdentificador.setText(patrimoniado.getTombo());
        } else if (campoIdentificador != null && item instanceof ItemConsumo consumo) {
            campoIdentificador.setText(String.valueOf(consumo.getId()));
        }


        campoCategoria.setText(item.getCategoria());

        campoLocalOrigem.setText(
                String.valueOf(item.getLocal())
        );

        campoStatus.setText(
                String.valueOf(item.getStatus())
        );
        if (!(campoDescricao == null)) {
            campoDescricao.setText(item.getDescricao());
        }

        campoQuantidade.setText(
                Integer.toString(item.getQuantidade())
        );

        campoEntrada.setText(
                DataFormatada.dataFormat(item.getEntrada())
        );

        if(!(imagemPatrimonio == null)){
            imagemPatrimonio.setImage(
                    SeletorFoto.carregarReduzida(
                            new File(item.getFoto()), 272
                    )
            );
        }

        labelSemFoto.setVisible(false);
    }

    public static void carregarDados(
            Item item,
            TextField campoNome,
            TextField campoIdentificador,
            TextField campoNumeroSerie,
            TextField campoCategoria,
            TextField campoLocalOrigem,
            ComboBox<Status> comboStatus,
            TextArea campoDescricao,
            TextField campoQuantidade,
            TextField campoEntrada,
            ImageView imagemPatrimonio,
            Label labelSemFoto
    ) {
        campoNome.setText(item.getNome());
        if (campoNumeroSerie != null && item instanceof ItemPatrimoniado patrimoniado) {
            campoNumeroSerie.setText(patrimoniado.getNumeroSerie());
        }

        if (campoIdentificador != null && item instanceof ItemPatrimoniado patrimoniado) {
            campoIdentificador.setText(patrimoniado.getTombo());
        } else if (campoIdentificador != null && item instanceof ItemConsumo consumo) {
            campoIdentificador.setText(String.valueOf(consumo.getId()));
        }
        campoCategoria.setText(item.getCategoria());

        campoLocalOrigem.setText(
                String.valueOf(item.getLocal())
        );

        ComboBoxHelper.carregarStatus(comboStatus);
        if (!(campoDescricao == null)) {
            campoDescricao.setText(item.getDescricao());
        }
        if (!(campoQuantidade == null)) {
            campoQuantidade.setText(
                    Integer.toString(item.getQuantidade())
            );
        }
        if (!(campoEntrada == null)) {
            campoEntrada.setText(
                    DataFormatada.dataFormat(item.getEntrada())
            );
        }
        imagemPatrimonio.setImage(
                SeletorFoto.carregarReduzida(
                        new File(item.getFoto()), 272
                )
        );

        labelSemFoto.setVisible(false);
    }

}
