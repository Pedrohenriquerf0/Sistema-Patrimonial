package sys.patrimonio.service;

import sys.patrimonio.config.AppContext;
import sys.patrimonio.model.MovimentacaoItemConsumo;
import sys.patrimonio.repository.MovimentacaoRepositorio;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    public List<MovimentacaoItemConsumo> buscarPorID() {
        Long id = Long.parseLong(pesquisa);
        Optional<MovimentacaoItemConsumo> optionalMovimentacao = Optional.ofNullable(this.movimentacaoRepositorio.buscarPorId(id));
        return optionalMovimentacao.map(List::of).orElseGet(List::of);
    }


    public List<MovimentacaoItemConsumo> listarTodos() {
        return this.movimentacaoRepositorio.listarTodos();
    }
}
