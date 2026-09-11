package sys.patrimonio.repository;

import sys.patrimonio.model.Item;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPatrimonio {
    private static List<Item> patrimonioList = new ArrayList<>();

    public static void adicionarPatrimonio(Item item) {
        patrimonioList.add(item);
    }

    public static void getPatrimonioList(Item item, int quantidade){
        int index = patrimonioList.indexOf(item);
        patrimonioList.get(index).setQuantidade(item.getQuantidade() - quantidade);
    }

    public static void getPatrimonioList(Item item, Localidade novolocal){
        int index = patrimonioList.indexOf(item);
        patrimonioList.get(index).setLocal(novolocal);
    }

    public static void getPatrimonioList(Item item, Status novostatus){
        int index = patrimonioList.indexOf(item);
        patrimonioList.get(index).setStatus(novostatus);
    }

    public static List<Item> getPatrimonioList(){
        return patrimonioList;
    }

    public static boolean existePatrimonio(Item item){
        return patrimonioList.contains(item);
    }
}
