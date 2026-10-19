package sys.patrimonio.util;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sys.patrimonio.model.ItemPatrimoniado;

import java.io.File;

public class PatrimonioLoader {


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
        if(!(campoDescricao == null)){
            campoDescricao.setText(patrimoniado.getDescricao());
        }

        campoQuantidade.setText(
                Integer.toString(patrimoniado.getQuantidade())
        );

        campoEntrada.setText(
                DataFormatada.dataFormat(patrimoniado.getEntrada())
        );

        imagemPatrimonio.setImage(
                SeletorFoto.carregarReduzida(
                        new File(patrimoniado.getFoto()), 272
                )
        );

        labelSemFoto.setVisible(false);
    }

}
