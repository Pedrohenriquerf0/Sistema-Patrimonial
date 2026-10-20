package sys.patrimonio.util;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.model.Status;

import java.io.File;

public final class PatrimonioLoader {


    public static void carregarDados(
            ItemPatrimoniado patrimoniado,
            TextField campoNome,
            TextField campoTombo,
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
        campoNome.setText(patrimoniado.getNome());
        campoTombo.setText(patrimoniado.getTombo());
        campoNumeroSerie.setText(patrimoniado.getNumeroSerie());
        campoCategoria.setText(patrimoniado.getCategoria());

        campoLocalOrigem.setText(
                String.valueOf(patrimoniado.getLocal())
        );

        campoStatus.setText(
                String.valueOf(patrimoniado.getStatus())
        );
        if (!(campoDescricao == null)) {
            campoDescricao.setText(patrimoniado.getDescricao());
        }

        campoQuantidade.setText(
                Integer.toString(patrimoniado.getQuantidade())
        );

        campoEntrada.setText(
                DataFormatada.dataFormat(patrimoniado.getEntrada())
        );

        if(!(imagemPatrimonio == null)){
            imagemPatrimonio.setImage(
                    SeletorFoto.carregarReduzida(
                            new File(patrimoniado.getFoto()), 272
                    )
            );
        }

        labelSemFoto.setVisible(false);
    }

    public static void carregarDados(
            ItemPatrimoniado patrimoniado,
            TextField campoNome,
            TextField campoTombo,
            TextField campoNumeroSerie,
            TextField campoCategoria,
            TextField campoLocalOrigem,
            ComboBox<Status> comboStatus,
            TextField campoDescricao,
            TextField campoQuantidade,
            TextField campoEntrada,
            ImageView imagemPatrimonio,
            Label labelSemFoto
    ) {
        campoNome.setText(patrimoniado.getNome());
        campoTombo.setText(patrimoniado.getTombo());
        campoNumeroSerie.setText(patrimoniado.getNumeroSerie());
        campoCategoria.setText(patrimoniado.getCategoria());

        campoLocalOrigem.setText(
                String.valueOf(patrimoniado.getLocal())
        );

        ComboBoxHelper.carregarStatus(comboStatus);
        if (!(campoDescricao == null)) {
            campoDescricao.setText(patrimoniado.getDescricao());
        }
        if (!(campoQuantidade == null)) {
            campoQuantidade.setText(
                    Integer.toString(patrimoniado.getQuantidade())
            );
        }
        if (!(campoEntrada == null)) {
            campoEntrada.setText(
                    DataFormatada.dataFormat(patrimoniado.getEntrada())
            );
        }
        imagemPatrimonio.setImage(
                SeletorFoto.carregarReduzida(
                        new File(patrimoniado.getFoto()), 272
                )
        );

        labelSemFoto.setVisible(false);
    }

}
