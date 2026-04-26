package sys.patrimonio.repository;

import sys.patrimonio.model.ItemPatrimonio;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPatrimonio {
    private static List<ItemPatrimonio> patrimonioList = new ArrayList<>();

    public static void adicionarPatrimonio(ItemPatrimonio itemPatrimonio) {
        patrimonioList.add(itemPatrimonio);
    }

    public static void getPatrimonioList(ItemPatrimonio itemPatrimonio, int quantidade){
        int index = patrimonioList.indexOf(itemPatrimonio);
        patrimonioList.get(index).setQuantidade(itemPatrimonio.getQuantidade() - quantidade);
    }

    public static void getPatrimonioList(ItemPatrimonio itemPatrimonio, Localidade novolocal){
        int index = patrimonioList.indexOf(itemPatrimonio);
        patrimonioList.get(index).setLocal(novolocal);
    }

    public static void getPatrimonioList(ItemPatrimonio itemPatrimonio, Status novostatus){
        int index = patrimonioList.indexOf(itemPatrimonio);
        patrimonioList.get(index).setStatus(novostatus);
    }

    public static List<ItemPatrimonio> getPatrimonioList(){
        return patrimonioList;
    }

    public static boolean existePatrimonio(ItemPatrimonio itemPatrimonio){
        return patrimonioList.contains(itemPatrimonio);
    }
}
