package sys.patrimonio.service;

import sys.patrimonio.config.AppContext;
import sys.patrimonio.model.MovimentacaoItemConsumo;
import sys.patrimonio.repository.MovimentacaoRepositorio;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MovimentacoesConsulta {
    private MovimentacaoRepositorio movimentacaoRepositorio = AppContext.getMovimentacaoRepositorio();

    public MovimentacoesConsulta() {
    }

    public List<MovimentacaoItemConsumo> movimentacoesList5Last() throws SQLException {
        return movimentacaoRepositorio.buscarUltimasInsercoes();
    }

    public List<MovimentacaoItemConsumo> buscarPorID(Long id) {
        Optional<MovimentacaoItemConsumo> optionalMovimentacao = Optional.ofNullable(this.movimentacaoRepositorio.buscarPorId(id));
        return optionalMovimentacao.map(List::of).orElseGet(List::of);
    }


    public List<MovimentacaoItemConsumo> listarTodos() {
        return this.movimentacaoRepositorio.listarTodos();
    }


    public Map<String, String> listarTotalData() throws SQLException{
        return movimentacaoRepositorio.listarTotalData();
    }
}
