package sys.patrimonio.util;

import sys.patrimonio.model.Cautela;


public final class Processo {

    public static String emissorCautela(){
        return System.getProperty("user.name").replace(".", " ").toUpperCase();
    }

    public static String usuarioOS(){
        return System.getProperty("user.home");
    }

    public static String descricaoFormatada(Cautela cautela){
        return cautela.getItemPatrimoniado().getDescricao() + ", n/s: " + cautela.getItemPatrimoniado().getNumeroSerie();
    }


    public static String IDFormatada(Long idcautela, int ano){
        String idFormatada = String.format("%04d/%d", idcautela, ano);
        return idFormatada;
    }

}
