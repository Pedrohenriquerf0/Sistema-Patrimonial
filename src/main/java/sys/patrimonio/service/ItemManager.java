package sys.patrimonio.service;

import sys.patrimonio.model.ItemPatrimonio;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.repository.RepositorioPatrimonio;
import sys.patrimonio.util.DataFormatada;


public class ItemManager {
    public void adcionarItem(ItemPatrimonio itemPatrimonio) {
        RepositorioPatrimonio.adicionarPatrimonio(itemPatrimonio);
    }

    public void remanejaItem(ItemPatrimonio itemPatrimonio, Localidade novoLocal) {
        if (RepositorioPatrimonio.existePatrimonio(itemPatrimonio) && itemPatrimonio.getLocal() != novoLocal) {
            RepositorioPatrimonio.getPatrimonioList(itemPatrimonio, novoLocal);
            System.out.println("Remanejado com sucesso com sucesso" + DataFormatada.dataAgora());
        }
    }

    public void registroSaida(ItemPatrimonio itemPatrimonio, int quantidade) { // atualiza a quantidade
        if (RepositorioPatrimonio.existePatrimonio(itemPatrimonio) && itemPatrimonio.getQuantidade() >= quantidade) {
            RepositorioPatrimonio.getPatrimonioList(itemPatrimonio, quantidade);
            System.out.println("Saida registrada e atualizada quantidade com sucesso" + DataFormatada.dataAgora());
        }
    }

    public void mudarStatus(ItemPatrimonio itemPatrimonio, Status novoStatus){
        if(RepositorioPatrimonio.existePatrimonio(itemPatrimonio) && itemPatrimonio.getStatus() != novoStatus){
            RepositorioPatrimonio.getPatrimonioList(itemPatrimonio, novoStatus);
            System.out.println("Mudança de Status feita com sucesso");
        }
    }

    public void listagem(){
        for(ItemPatrimonio patrimonio: RepositorioPatrimonio.getPatrimonioList()){
            System.out.println(patrimonio.getNome());
            System.out.println(patrimonio.getEntrada());
            System.out.println(patrimonio.getTombo());
            System.out.println(patrimonio.getDescricao());
            System.out.println(patrimonio.getLocal());
            System.out.println(patrimonio.getCategoria());
            System.out.println(patrimonio.getStatus());
        }
    }
}
