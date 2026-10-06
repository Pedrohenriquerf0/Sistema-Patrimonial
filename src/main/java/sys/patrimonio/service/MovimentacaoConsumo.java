package sys.patrimonio.service;

import sys.patrimonio.config.AppContext;
import sys.patrimonio.exceptions.SaldoInsuficienteException;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.MovimentacaoItemConsumo;
import sys.patrimonio.model.TipoMovimentacao;
import sys.patrimonio.repository.MovimentacaoRepositorio;

import java.util.List;

public class MovimentacaoConsumo {
    private final MovimentacaoRepositorio movimentacaoRepositorio;

    public MovimentacaoConsumo() {
        this.movimentacaoRepositorio = AppContext.getMovimentacaoRepositorio();
    }

    public MovimentacaoItemConsumo buscarPorID(long id){
        return this.movimentacaoRepositorio.buscarPorId(id);
    }


    public List<MovimentacaoItemConsumo> listarTodos(){
        return this.movimentacaoRepositorio.listarTodos();
    }

    public void registrarSaida(ItemConsumo item, int quantidade, Localidade destino) {
        ConsumoManager consumoManager = new ConsumoManager();
        ItemConsumo itemConsumo = consumoManager.buscarPorID(item.getId());

        if (quantidade > itemConsumo.getQuantidade()) {
            throw new SaldoInsuficienteException("Saida maior que o estoque");  // TODO - criar exceptions para esse tipo
        }
        itemConsumo.setQuantidade(itemConsumo.getQuantidade()- quantidade);
        consumoManager.atualizar(itemConsumo);

        MovimentacaoItemConsumo movimentacaoSaida = new MovimentacaoItemConsumo(destino, item, quantidade, TipoMovimentacao.SAIDA);
        this.movimentacaoRepositorio.salvar(movimentacaoSaida);
    }

    public void registrarEntrada(ItemConsumo item, int quantidade) {
        ConsumoManager consumoManager = new ConsumoManager();
        ItemConsumo itemConsumo = consumoManager.buscarPorID(item.getId());

        if (quantidade <=0) {
            throw new IllegalArgumentException("quantidade tem que ser positiva");  // TODO - criar exceptions para esse tipo
        }
        itemConsumo.setQuantidade(itemConsumo.getQuantidade() + quantidade);
        consumoManager.atualizar(itemConsumo);

        MovimentacaoItemConsumo movimentacaoEntrada = new MovimentacaoItemConsumo(Localidade.PATRIMONIO, item, quantidade, TipoMovimentacao.ENTRADA); // POR QUE LOCALIDADE.PATRIMONI? ASSIM QUE UM ITEM CHEGAR ELE VAI DIRETO PRO PATRIMONI ANSTE DE SER DISTRIBUIDO
        this.movimentacaoRepositorio.salvar(movimentacaoEntrada);
    }
}
