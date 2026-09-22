package sys.patrimonio.repository;

import sys.patrimonio.model.Cautela;

import java.util.List;

class CautelaDAO implements CautelaRepositorio {


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
