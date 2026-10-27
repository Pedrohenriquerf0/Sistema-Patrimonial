package sys.patrimonio.service;

import sys.patrimonio.config.AppContext;
import sys.patrimonio.model.Cautela;
import sys.patrimonio.repository.CautelaRepositorio;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class CautelasConsulta {
    private final CautelaRepositorio cautelaRepositorio = AppContext.getCautelaRepositorio();

    public CautelasConsulta() {
    }

    public List<Cautela> cautelaList5Last() throws SQLException {
        return cautelaRepositorio.buscarUltimasInsercoes();
    }

    public List<Cautela> buscarPorID(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID não pode se menor que zero");
        }
        Optional<Cautela> optionalCautela = Optional.ofNullable(cautelaRepositorio.buscarPorId(id));
        return optionalCautela.map(List::of).orElseGet(List::of);
    }
}
