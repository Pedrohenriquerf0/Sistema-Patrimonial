package sys.patrimonio.repository;

import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.model.Cautela;

import java.util.List;

public class CautelaDAO implements CautelaRepositorio {
    private final ConnectionFactory connectionFactory;

    public CautelaDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Cautela buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<Cautela> listarTodos() {
        return List.of();
    }

    @Override
    public void salvar(Cautela obj) {

    }

    @Override
    public Long idCautela(Cautela obj) {
        return 0L;
    }
}
