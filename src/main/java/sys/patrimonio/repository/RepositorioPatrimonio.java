package sys.patrimonio.repository;

import sys.patrimonio.model.Departamento;
import sys.patrimonio.model.ItemsPatrimonio;
import sys.patrimonio.model.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPatrimonio implements MetodosPatrimonio {
    private static List<ItemsPatrimonio> patrimonioList = new ArrayList<>();

    @Override
    public void adiconarItems(ItemsPatrimonio item) {
        patrimonioList.add(item);
        System.out.println("Item adicionado com sucesso");
    }

    @Override
    public void registroSaida(ItemsPatrimonio item) { // não remove item, so remaneja
        patrimonioList.remove(item);
        System.out.println("Item removido com sucesso" + LocalDate.now());
    }

    @Override
    public void registroSaida(ItemsPatrimonio item, int quantidade) { // atualiza a quantidade
        if(patrimonioList.contains(item) && item.getQuantidade() >= quantidade){
            ItemsPatrimonio itemsPatrimonio = item;
            patrimonioList.remove(item);
            itemsPatrimonio.setQuantidade(itemsPatrimonio.getQuantidade() - quantidade);
            patrimonioList.add(itemsPatrimonio);
            System.out.println("Item removido com sucesso" + LocalDate.now());
        }
    }


    @Override
    public void itemDanificado(ItemsPatrimonio item) {
        if(patrimonioList.contains(item)){
            ItemsPatrimonio itemsPatrimonio = item;
            patrimonioList.remove(item);
            itemsPatrimonio.setStatus(Status.DANIFICADO);
            patrimonioList.add(itemsPatrimonio);
            System.out.println("Status Para danificado com sucesso");
        }
    }

    @Override
    public void mudarStatus(ItemsPatrimonio item, Status status) {
        if(patrimonioList.contains(item)){
            for(ItemsPatrimonio bens: patrimonioList){
                if(bens.equals(item)){
                    bens.setStatus(status);
                }
            }
        }
    }

    @Override
    public void transferencia(ItemsPatrimonio item, Departamento departamento) { // cria uma função que vai juntar essa com a de remanja
        if(patrimonioList.contains(item)){
            for(ItemsPatrimonio bens: patrimonioList){
                if(bens.equals(item)) {
                    bens.setDepartamento(departamento);
                }
            }
        }
    }

    @Override
    public void listagem(){
        for(ItemsPatrimonio patrimonio: patrimonioList){
            System.out.println(patrimonio);
        }
    }

    @Override
    public void relatorio() {
        for(ItemsPatrimonio bens: patrimonioList){
            System.out.println(bens.toString());
        }
    }
}
