package sys.patrimonio.repository;

import sys.patrimonio.model.ItemPatrimonio;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPatrimonio implements MetodosPatrimonio {
    private static List<ItemPatrimonio> patrimonioList = new ArrayList<>();

    @Override
    public void adiconarItems(ItemPatrimonio item) {
        patrimonioList.add(item);
        System.out.println("Item adicionado com sucesso");
    }

    @Override
    public void registroSaida(ItemPatrimonio item) { // não remove item, so remaneja
        patrimonioList.remove(item);
        System.out.println("Item removido com sucesso" + LocalDate.now());
    }

    @Override
    public void registroSaida(ItemPatrimonio item, int quantidade) { // atualiza a quantidade
        if(patrimonioList.contains(item) && item.getQuantidade() >= quantidade){
            ItemPatrimonio itemsPatrimonio = item;
            patrimonioList.remove(item);
            itemsPatrimonio.setQuantidade(itemsPatrimonio.getQuantidade() - quantidade);
            patrimonioList.add(itemsPatrimonio);
            System.out.println("Item removido com sucesso" + LocalDate.now());
        }
    }


    @Override
    public void itemDanificado(ItemPatrimonio item) {
        if(patrimonioList.contains(item)){
            ItemPatrimonio itemsPatrimonio = item;
            patrimonioList.remove(item);
            itemsPatrimonio.setStatus(Status.DANIFICADO);
            patrimonioList.add(itemsPatrimonio);
            System.out.println("Status Para danificado com sucesso");
        }
    }

    @Override
    public void mudarStatus(ItemPatrimonio item, Status status) {
        if(patrimonioList.contains(item)){
            for(ItemPatrimonio bens: patrimonioList){
                if(bens.equals(item)){
                    bens.setStatus(status);
                }
            }
        }
    }

    @Override
    public void transferencia(ItemPatrimonio item, Localidade local) { // cria uma função que vai juntar essa com a de remanja
        if(patrimonioList.contains(item)){
            for(ItemPatrimonio bens: patrimonioList){
                if(bens.equals(item)) {
                    bens.setLocal(local);
                }
            }
        }
    }

    @Override
    public void listagem(){
        for(ItemPatrimonio patrimonio: patrimonioList){
            System.out.println(patrimonio.getNome());
            System.out.println(patrimonio.getEntrada());
            System.out.println(patrimonio.getTombo());
            System.out.println(patrimonio.getDescricao());
            System.out.println(patrimonio.getLocal());
            System.out.println(patrimonio.getCategoria());
            System.out.println(patrimonio.getStatus());
        }
    }

    @Override
    public void relatorio() {
        for(ItemPatrimonio patrimonio: patrimonioList){
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
