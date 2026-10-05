package sys.patrimonio.service;

import sys.patrimonio.config.AppContext;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.repository.ConsumoRepositorio;

import java.util.List;

public class ConsumoManager {
    private final ConsumoRepositorio consumoRepositorio;

    public ConsumoManager() {
        this.consumoRepositorio = AppContext.getConsumoRepositorio();
    }

    public void salvar(ItemConsumo itemConsumo){
        if(itemConsumo == null){
            throw new IllegalArgumentException("item Consumo não pode ser nulo");
        }
        this.consumoRepositorio.salvar(itemConsumo);
    }

    public void excluir(ItemConsumo itemConsumo){
        if(itemConsumo == null){
            throw new IllegalArgumentException("item Consumo não pode ser nulo");
        }
        this.consumoRepositorio.excluir(itemConsumo.getId());
    }

    public List<ItemConsumo> listar(){
        return this.consumoRepositorio.listar();
    }

    public void atualizar(ItemConsumo itemConsumo){
        if(itemConsumo == null){
            throw new IllegalArgumentException("item Consumo não pode ser nulo");
        }
        this.consumoRepositorio.atualizar(itemConsumo);
    }

    public ItemConsumo buscarPorID(long id){
        if(id <= 0){
            throw new IllegalArgumentException("item Consumo não pode ter ID menor que zero");
        }
        return this.consumoRepositorio.buscarPorId(id);
    }

    public List<ItemConsumo> buscarPorNome(String nome){
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("item Consumo não pode ser nulo");
        }
        return this.consumoRepositorio.buscarPorNome(nome);
    }
}
