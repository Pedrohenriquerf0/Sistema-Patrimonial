package sys.patrimonio.service;


import sys.patrimonio.config.AppContext;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.repository.PatrimoniadoRepositorio;

import java.util.List;

public class PatrimoniadoManager {
    private final PatrimoniadoRepositorio patrimoniadoRepositorio;

    public PatrimoniadoManager() {
        this.patrimoniadoRepositorio = AppContext.getPatrimoniadoRepositorio();
    }

    public void atualizar(ItemPatrimoniado patrimoniado) {
        if (patrimoniado == null) {
            throw new IllegalArgumentException("Item patrimoniado não pode ser nulo");
        }
        patrimoniadoRepositorio.atualizar(patrimoniado);
    }

    public void salvar(ItemPatrimoniado patrimoniado){
        if(patrimoniado == null){
            throw new IllegalArgumentException("item Patrimoniado não pode ser nulo");
        }
        patrimoniadoRepositorio.salvar(patrimoniado);
    }

    public void excluir(ItemPatrimoniado patrimoniado){
        if(patrimoniado == null){
            throw new IllegalArgumentException("item Patrimoniado não pode ser nulo");
        }
        patrimoniadoRepositorio.excluir(patrimoniado.getTombo());
    }

    public List<ItemPatrimoniado> listar(){
        return patrimoniadoRepositorio.listar();
    }
    public ItemPatrimoniado buscarPorID(String tombo){
        if(tombo == null || tombo.isBlank()){
            throw new IllegalArgumentException("item Patrimoniado não pode ser nulo");
        }
        return patrimoniadoRepositorio.buscarPorId(tombo);
    }
    public List<ItemPatrimoniado> buscarPorNome(String nome){
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("item Patrimoniado não pode ser nulo");
        }
        return patrimoniadoRepositorio.buscarPorNome(nome);
    }
    public ItemPatrimoniado buscarPorNS(String numeroSerie){
        if(numeroSerie == null || numeroSerie.isBlank()){
            throw new IllegalArgumentException("item Patrimoniado não pode ser nulo");
        }
        return patrimoniadoRepositorio.buscarPorNS(numeroSerie);
    };

}
