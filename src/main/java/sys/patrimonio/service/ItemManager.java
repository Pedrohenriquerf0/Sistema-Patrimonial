package sys.patrimonio.service;

import sys.patrimonio.model.Item;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.repository.RepositorioPatrimonio;
import sys.patrimonio.util.DataFormatada;
import java.util.List;


public class ItemManager {
    public void adicionarItem(Item item) {
        RepositorioPatrimonio.adicionarPatrimonio(item);
    }

    public void remanejaItem(Item item, Localidade novoLocal) {
        if (RepositorioPatrimonio.existePatrimonio(item) && item.getLocal() != novoLocal) {
            RepositorioPatrimonio.getPatrimonioList(item, novoLocal);
            System.out.println("Remanejado com sucesso com sucesso " + DataFormatada.dataAgora());
        }
    }

    public void registroSaida(Item item, int quantidade) { // atualiza a quantidade
        if (RepositorioPatrimonio.existePatrimonio(item) && item.getQuantidade() >= quantidade) {
            RepositorioPatrimonio.getPatrimonioList(item, quantidade);
            System.out.println("Saida registrada e atualizada quantidade com sucesso " + DataFormatada.dataAgora());
        }
    }

    public void mudarStatus(Item item, Status novoStatus) {
        if (RepositorioPatrimonio.existePatrimonio(item) && item.getStatus() != novoStatus) {
            RepositorioPatrimonio.getPatrimonioList(item, novoStatus);
            System.out.println("Mudança de Status feita com sucesso");
        }
    }

    public List<Item> teste(){ // remover no futuro
        return RepositorioPatrimonio.getPatrimonioList();
    }
    public void listagem() {
        List<Item> itemList = RepositorioPatrimonio.getPatrimonioList();

        if (itemList.isEmpty()) {
            System.out.println("sem dados;");
            return;
        }
        itemList.forEach(System.out::println);
    }
}
