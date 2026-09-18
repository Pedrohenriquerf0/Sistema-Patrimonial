package sys.patrimonio.repository;

import sys.patrimonio.model.Cautela;

public interface CautelaRepositorio extends Repositorio<Cautela, Long>{
    @Override
    void listar(Cautela objeto);

    @Override
    void salvar(Cautela objeto);

    abstract Long idCautela(Cautela objeto);
}
