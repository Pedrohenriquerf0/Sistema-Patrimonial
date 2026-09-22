package sys.patrimonio.repository;

import sys.patrimonio.model.Cautela;

import java.util.List;

public interface CautelaRepositorio extends RepositorioSomenteInsercao<Cautela, Long> {
    @Override
    Cautela buscarPorId(Long aLong);

    @Override
    List<Cautela> listarTodos();

    @Override
    void salvar(Cautela obj);

    Long idCautela(Cautela cautela);
}
