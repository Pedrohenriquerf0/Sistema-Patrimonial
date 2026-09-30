package sys.patrimonio.repository;


import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.model.MovimentacaoItemConsumo;

import java.util.List;

public class MovimentacaoDAO implements MovimentacaoRepositorio {
    private final ConnectionFactory connectionFactory;

    public MovimentacaoDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

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
