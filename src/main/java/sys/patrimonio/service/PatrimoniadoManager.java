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

        validar(patrimoniado);
        patrimoniadoRepositorio.atualizar(patrimoniado);
    }

    public void salvar(ItemPatrimoniado patrimoniado){
        if(patrimoniado == null){
            throw new IllegalArgumentException("item Patrimoniado não pode ser nulo");
        }

        validar(patrimoniado);
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

    private void validar(ItemPatrimoniado item) {
        if (item.getNome() == null || item.getNome().isBlank())
            throw new IllegalArgumentException("Nome é obrigatório.");

        if (item.getTombo() == null || item.getTombo().isBlank())
            throw new IllegalArgumentException("Tombo é obrigatório.");

        if (item.getNumeroSerie() == null || item.getNumeroSerie().isBlank())
            throw new IllegalArgumentException("Número de série é obrigatório.");

        if (item.getDescricao() == null || item.getDescricao().isBlank())
            throw new IllegalArgumentException("Descrição é obrigatória.");

        if (item.getFoto() == null || item.getFoto().isBlank())
            throw new IllegalArgumentException("Foto é obrigatória.");

        if (item.getLocal() == null)
            throw new IllegalArgumentException("Localidade é obrigatória.");

        if (item.getStatus() == null)
            throw new IllegalArgumentException("Status é obrigatório.");

        if (item.getCategoria() == null || item.getCategoria().isBlank())
            throw new IllegalArgumentException("Categoria é obrigatória.");
    }

}
