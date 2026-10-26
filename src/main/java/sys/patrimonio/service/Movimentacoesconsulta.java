package sys.patrimonio.service;

import sys.patrimonio.config.AppContext;
import sys.patrimonio.model.MovimentacaoItemConsumo;
import sys.patrimonio.repository.MovimentacaoRepositorio;

import java.sql.SQLException;
import java.util.List;

public class Movimentacoesconsulta {
    private MovimentacaoRepositorio movimentacaoRepositorio = AppContext.getMovimentacaoRepositorio();
    private String pesquisa;

    public Movimentacoesconsulta(String pesquisa) {
        this.pesquisa = pesquisa;
    }


    public Movimentacoesconsulta() {
    }

    public List<MovimentacaoItemConsumo> movimentacoesList5Last() throws SQLException {
        return movimentacaoRepositorio.buscarUltimasInsercoes();
    }
}
