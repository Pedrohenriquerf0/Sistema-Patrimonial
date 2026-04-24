package sys.patrimonio.repository;

import sys.patrimonio.model.Cautela;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCautela {
    private static List<Cautela> cautelaList = new ArrayList<>();

    public static void adicionarCautela(Cautela cautela){
        cautelaList.add(cautela);
    }


    public static int idCautela(Cautela cautela){
        return cautelaList.indexOf(cautela);
    }
}
