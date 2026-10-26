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

    public void salvar(ItemConsumo itemConsumo) {
        if (itemConsumo == null) {
            throw new IllegalArgumentException("item Consumo não pode ser nulo");
        }

        validar(itemConsumo);
        this.consumoRepositorio.salvar(itemConsumo);
    }

    public void excluir(ItemConsumo itemConsumo) {
        if (itemConsumo == null) {
            throw new IllegalArgumentException("item Consumo não pode ser nulo");
        }
        this.consumoRepositorio.excluir(itemConsumo.getId());
    }

    public List<ItemConsumo> listar() {
        return this.consumoRepositorio.listar();
    }

    public void atualizar(ItemConsumo itemConsumo) {
        if (itemConsumo == null) {
            throw new IllegalArgumentException("item Consumo não pode ser nulo");
        }

        validar(itemConsumo);
        this.consumoRepositorio.atualizar(itemConsumo);
    }

    public ItemConsumo buscarPorID(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("item Consumo não pode ter ID menor que zero");
        }
        return this.consumoRepositorio.buscarPorId(id);
    }


    private void validar(ItemConsumo item) {
        if (item.getNome() == null || item.getNome().isBlank())
            throw new IllegalArgumentException("Nome é obrigatório.");

        if (item.getQuantidade() <= 0)
            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero."
            );

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
