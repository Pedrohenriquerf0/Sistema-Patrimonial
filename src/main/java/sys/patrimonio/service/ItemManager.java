package sys.patrimonio.service;

import sys.patrimonio.model.ItemPatrimonio;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.repository.RepositorioPatrimonio;
import sys.patrimonio.util.DataFormatada;
import java.util.List;


public class ItemManager {
    public void adcionarItem(ItemPatrimonio itemPatrimonio) {
        RepositorioPatrimonio.adicionarPatrimonio(itemPatrimonio);
    }

    public void remanejaItem(ItemPatrimonio itemPatrimonio, Localidade novoLocal) {
        if (RepositorioPatrimonio.existePatrimonio(itemPatrimonio) && itemPatrimonio.getLocal() != novoLocal) {
            RepositorioPatrimonio.getPatrimonioList(itemPatrimonio, novoLocal);
            System.out.println("Remanejado com sucesso com sucesso " + DataFormatada.dataAgora());
        }
    }

    public void registroSaida(ItemPatrimonio itemPatrimonio, int quantidade) { // atualiza a quantidade
        if (RepositorioPatrimonio.existePatrimonio(itemPatrimonio) && itemPatrimonio.getQuantidade() >= quantidade) {
            RepositorioPatrimonio.getPatrimonioList(itemPatrimonio, quantidade);
            System.out.println("Saida registrada e atualizada quantidade com sucesso " + DataFormatada.dataAgora());
        }
    }

    public void mudarStatus(ItemPatrimonio itemPatrimonio, Status novoStatus) {
        if (RepositorioPatrimonio.existePatrimonio(itemPatrimonio) && itemPatrimonio.getStatus() != novoStatus) {
            RepositorioPatrimonio.getPatrimonioList(itemPatrimonio, novoStatus);
            System.out.println("Mudança de Status feita com sucesso");
        }
    }

    public List<ItemPatrimonio> teste(){ // remover no futuro
        return RepositorioPatrimonio.getPatrimonioList();
    }
    public void listagem() {
        List<ItemPatrimonio> itemPatrimonioList = RepositorioPatrimonio.getPatrimonioList();

        if (itemPatrimonioList.isEmpty()) {
            System.out.println("sem dados;");
            return;
        }
        itemPatrimonioList.forEach(System.out::println);
    }
}
