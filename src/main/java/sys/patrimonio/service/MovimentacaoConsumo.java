package sys.patrimonio.service;

import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.MovimentacaoItemConsumo;
import sys.patrimonio.model.TipoMovimentacao;

public class MovimentacaoConsumo {
    public void registrarSaida(ItemConsumo item, int quantidade, Localidade destino) {
        item.darBaixa(quantidade);
        MovimentacaoItemConsumo movimentacao = new MovimentacaoItemConsumo(destino, item, TipoMovimentacao.SAIDA);

    }

    public void registrarEntrada(ItemConsumo item, int quantidade) {
        item.reporEstoque(quantidade);
        MovimentacaoItemConsumo movimentacao = new MovimentacaoItemConsumo(Localidade.PATRIMONIO, item, TipoMovimentacao.ENTRADA); // POR QUE LOCALIDADE.PATRIMONI? ASSIM QUE UM ITEM CHEGAR ELE VAI DIRETO PRO PATRIMONI ANSTE DE SER DISTRIBUIDO

    }
}
