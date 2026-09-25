package sys.patrimonio.repository;

import sys.patrimonio.model.Cautela;

public interface CautelaRepositorio extends RepositorioSomenteInsercao<Cautela, Long> {

    Long idCautela(Cautela cautela);
}
