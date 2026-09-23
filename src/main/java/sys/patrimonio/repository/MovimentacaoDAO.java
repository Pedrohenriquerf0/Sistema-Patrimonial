package sys.patrimonio.repository;


import sys.patrimonio.model.MovimentacaoItemConsumo;

import java.util.List;

public class MovimentacaoDAO implements MovimentacaoRepositorio {

    @Override
    public MovimentacaoItemConsumo buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<MovimentacaoItemConsumo> listarTodos() {
        return List.of();
    }

    @Override
    public void salvar(MovimentacaoItemConsumo obj) {

    }
}
