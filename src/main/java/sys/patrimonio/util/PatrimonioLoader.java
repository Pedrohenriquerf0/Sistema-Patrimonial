package sys.patrimonio.util;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sys.patrimonio.model.*;

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

        if (!(imagemPatrimonio == null)) {
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


    public static void carregarDadosCautela(
            Cautela cautela,
            TextField campoNome,
            TextField campoIdentificador,
            TextField campoNumeroSerie,
            TextField campoCategoria,
            TextField campoLocalOrigem,
            TextField campoLocalDestino,
            TextField campoStatus,
            TextField campoQuantidade,
            TextField campoEntrada,
            TextField campoDataCautela,
            TextField campoEmissor,
            TextField campoIDCautela,
            TextField campoObservacao,
            ImageView imagemPatrimonio,
            Label labelSemFoto
    ) {
        campoNome.setText(cautela.getItemPatrimoniado().getNome());

        campoNumeroSerie.setText(cautela.getItemPatrimoniado().getNumeroSerie());

        campoIdentificador.setText(cautela.getItemPatrimoniado().getTombo());


        campoCategoria.setText(cautela.getItemPatrimoniado().getCategoria());

        campoLocalOrigem.setText(
                String.valueOf(cautela.getOrigem())
        );

        campoStatus.setText(
                String.valueOf(cautela.getItemPatrimoniado().getStatus())
        );

        campoQuantidade.setText(
                Integer.toString(cautela.getItemPatrimoniado().getQuantidade())
        );

        campoEntrada.setText(
                DataFormatada.dataFormat(cautela.getItemPatrimoniado().getEntrada())
        );

        campoLocalDestino.setText(
                String.valueOf(cautela.getDestino())
        );

        campoEmissor.setText(cautela.getEmissor());

        campoIDCautela.setText(cautela.getId());

        campoDataCautela.setText(cautela.getData());
        campoObservacao.setText(cautela.getObservacoes().orElse("-"));

        if (!(imagemPatrimonio == null)) {
            imagemPatrimonio.setImage(
                    SeletorFoto.carregarReduzida(
                            new File(cautela.getItemPatrimoniado().getFoto()), 272
                    )
            );
        }

        labelSemFoto.setVisible(false);
    }


    public static void carregarDadosMov(
            MovimentacaoItemConsumo mov,
            TextField campoNome,
            TextField campoIdentificador,
            TextField campoCategoria,
            TextField campoLocalDestino,
            TextField campoQuantidade,
            TextField campoEntrada,
            TextField campoDataMov,
            TextField campoEmissor,
            TextField campoIDMov,
            TextField campoTipo,
            ImageView imagemPatrimonio,
            Label labelSemFoto
    ) {
        campoNome.setText(mov.getItemConsumo().getNome());

        campoIdentificador.setText(String.valueOf(mov.getItemConsumo().getId()));


        campoCategoria.setText(mov.getItemConsumo().getCategoria());

        campoQuantidade.setText(
                Integer.toString(mov.getQuantidade())
        );

        campoEntrada.setText(
                DataFormatada.dataFormat(mov.getItemConsumo().getEntrada())
        );

        campoLocalDestino.setText(
                String.valueOf(mov.getDestino())
        );

        campoEmissor.setText(mov.getEmissor());

        campoIDMov.setText(String.valueOf(mov.getId()));

        campoDataMov.setText(mov.getData());

        campoTipo.setText(String.valueOf(mov.getTipoMovimentacao()));

        if (!(imagemPatrimonio == null)) {
            imagemPatrimonio.setImage(
                    SeletorFoto.carregarReduzida(
                            new File(mov.getItemConsumo().getFoto()), 272
                    )
            );
        }

        labelSemFoto.setVisible(false);
    }

}
