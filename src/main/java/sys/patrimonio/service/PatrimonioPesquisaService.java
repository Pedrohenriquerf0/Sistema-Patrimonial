package sys.patrimonio.service;

import sys.patrimonio.config.AppContext;
import sys.patrimonio.model.Item;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.repository.ConsumoRepositorio;
import sys.patrimonio.repository.PatrimoniadoRepositorio;

import java.util.List;
import java.util.stream.Stream;

public class PatrimonioPesquisaService {
    private final PatrimoniadoRepositorio patrimoniadoRepositorio;
    private final ConsumoRepositorio consumoRepositorio;
    private String pesquisa;

    public PatrimonioPesquisaService(String pesquisa) {
        this.pesquisa = pesquisa.trim();
        this.consumoRepositorio = AppContext.getConsumoRepositorio();
        this.patrimoniadoRepositorio = AppContext.getPatrimoniadoRepositorio();
    }

    public List<Item> pesquisa(){

        if(pesquisa.matches("\\d+")){
            return pesquisaConsumoID().stream().map(item -> (Item) item).toList();
        }else if(pesquisa.chars().anyMatch(Character::isDigit)){
            if(pesquisa.length() <= 10){
                return pesquisaPatrimoniadoTombo().stream().map(item -> (Item) item).toList();
            }else if(pesquisa.length() <= 15){
                return pesquisaPatrimoniadoNS().stream().map(item -> (Item) item).toList();
            }
        }

        return Stream.concat(pesquisaConsumoNome().stream(), pesquisaPatrimoniaddoNome().stream())
                .map(item ->  (Item) item).toList();
    }

    private List<ItemConsumo> pesquisaConsumoNome(){
        return consumoRepositorio.buscarPorNome(pesquisa);
    }

    private List<ItemConsumo> pesquisaConsumoID(){
        Long id = Long.parseLong(pesquisa);
        return List.of(consumoRepositorio.buscarPorId(id));
    }

    private List<ItemPatrimoniado> pesquisaPatrimoniaddoNome(){
        return patrimoniadoRepositorio.buscarPorNome(pesquisa);
    }

    private List<ItemPatrimoniado> pesquisaPatrimoniadoTombo(){
        return List.of(patrimoniadoRepositorio.buscarPorId(pesquisa));
    }

    private List<ItemPatrimoniado> pesquisaPatrimoniadoNS(){
        return List.of(patrimoniadoRepositorio.buscarPorNS(pesquisa));
    }




}
