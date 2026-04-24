package sys.patrimonio.util;

import sys.patrimonio.model.Cautela;

public final class CautelaProcesso {

    public static String emissorCautela(){
        return System.getProperty("user.name").replace(".", " ").toUpperCase();
    }

    public static String usuarioOS(){
        return System.getProperty("user.home");
    }

    public static String descricaoFormatada(Cautela cautela){
        return cautela.getItemPatrimonio().getDescricao() + ", n/s: " + cautela.getItemPatrimonio().getNumeroSerie();
    }


    public static String IDFormatada(int idcautela){
        String idFormatada = String.format("%04d/%s", idcautela, DataFormatada.anoAgora());
        return idFormatada;
    }

}
