package sys.patrimonio.service;

import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.MovimentacaoItemConsumo;
import sys.patrimonio.model.TipoMovimentacao;
import sys.patrimonio.repository.MovimentacaoRepositorio;

public class MovimentacaoConsumo {
    private  MovimentacaoRepositorio movimentacaoRepositorio;

    public void registrarSaida(ItemConsumo item, int quantidade, Localidade destino, MovimentacaoRepositorio movimentacaoRepositorio) {
        this.movimentacaoRepositorio = movimentacaoRepositorio;
        item.darBaixa(quantidade);
        MovimentacaoItemConsumo novaMovimentacao = new MovimentacaoItemConsumo(destino, item, TipoMovimentacao.SAIDA);
        this.movimentacaoRepositorio.salvar(novaMovimentacao);

    }

    public void registrarEntrada(ItemConsumo item, int quantidade) {
        item.reporEstoque(quantidade);
        MovimentacaoItemConsumo movimentacao = new MovimentacaoItemConsumo(Localidade.PATRIMONIO, item, TipoMovimentacao.ENTRADA); // POR QUE LOCALIDADE.PATRIMONI? ASSIM QUE UM ITEM CHEGAR ELE VAI DIRETO PRO PATRIMONI ANSTE DE SER DISTRIBUIDO

    }
}
