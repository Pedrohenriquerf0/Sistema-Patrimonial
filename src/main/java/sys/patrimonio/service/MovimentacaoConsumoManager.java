package sys.patrimonio.service;

import sys.patrimonio.config.AppContext;
import sys.patrimonio.exceptions.SaldoInsuficienteException;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.MovimentacaoItemConsumo;
import sys.patrimonio.model.TipoMovimentacao;
import sys.patrimonio.repository.MovimentacaoRepositorio;

import java.util.List;

public class MovimentacaoConsumoManager {
    private final MovimentacaoRepositorio movimentacaoRepositorio;

    public MovimentacaoConsumoManager() {
        this.movimentacaoRepositorio = AppContext.getMovimentacaoRepositorio();
    }

    public MovimentacaoItemConsumo buscarPorID(long id) {
        return this.movimentacaoRepositorio.buscarPorId(id);
    }


    public List<MovimentacaoItemConsumo> listarTodos() {
        return this.movimentacaoRepositorio.listarTodos();
    }

    public void registrarSaida(ItemConsumo item, int quantidade, Localidade destino) {
        ItemConsumo itemConsumo = new ConsumoManager().buscarPorID(item.getId());

        if (quantidade > itemConsumo.getQuantidade() || quantidade <= 0) {
            throw new SaldoInsuficienteException("Saida maior que o estoque, nem menor que zero");  // TODO - criar exceptions para esse tipo
        }
        item.setQuantidade(itemConsumo.getQuantidade() - quantidade);
        this.salvar(new MovimentacaoItemConsumo(destino, item, quantidade, TipoMovimentacao.SAIDA));
    }

    public void registrarEntrada(ItemConsumo item, int quantidade) {
        ItemConsumo itemConsumo = new ConsumoManager().buscarPorID(item.getId());

        if (quantidade <= 0) {
            throw new IllegalArgumentException( "A quantidade deve ser positiva.");  // TODO - criar exceptions para esse tipo
        }
        item.setQuantidade(itemConsumo.getQuantidade() + quantidade);
        this.salvar(new MovimentacaoItemConsumo(Localidade.PATRIMONIO, item, quantidade, TipoMovimentacao.ENTRADA)); // POR QUE LOCALIDADE.PATRIMONI? ASSIM QUE UM ITEM CHEGAR ELE VAI DIRETO PRO PATRIMONI ANSTE DE SER DISTRIBUIDO
    }

    private void salvar(MovimentacaoItemConsumo movimentacaoItemConsumo) {
        this.movimentacaoRepositorio.salvar(movimentacaoItemConsumo);
    }
}
