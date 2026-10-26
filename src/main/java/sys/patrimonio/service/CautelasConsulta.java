package sys.patrimonio.service;

import sys.patrimonio.config.AppContext;
import sys.patrimonio.model.Cautela;
import sys.patrimonio.repository.CautelaRepositorio;

import java.sql.SQLException;
import java.util.List;


public class CautelasConsulta {
    private final CautelaRepositorio cautelaRepositorio = AppContext.getCautelaRepositorio();
    private String cautelaID;

    public CautelasConsulta(String cautelaID) {
        this.cautelaID = cautelaID;
    }

    public CautelasConsulta() {
    }

    public List<Cautela> cautelaList5Last() throws SQLException {
        return cautelaRepositorio.buscarUltimasInsercoes();
    }
}
