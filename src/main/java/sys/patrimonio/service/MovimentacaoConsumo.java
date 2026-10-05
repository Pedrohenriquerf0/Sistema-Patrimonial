package sys.patrimonio.service;

import sys.patrimonio.config.AppContext;
import sys.patrimonio.exceptions.SaldoInsuficienteException;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.MovimentacaoItemConsumo;
import sys.patrimonio.model.TipoMovimentacao;
import sys.patrimonio.repository.MovimentacaoRepositorio;

public class MovimentacaoConsumo {
    private MovimentacaoRepositorio movimentacaoRepositorio;

    public MovimentacaoConsumo() {
        this.movimentacaoRepositorio = AppContext.getMovimentacaoRepositorio();
    }

    /* TODO IMPLEMENTAÇÃO COMEÇA QUANDO CONSUMO MANAGE ESTIVE QUASE PRONTO
     * buscarPorId
     * listarTodos()
     * salvar
     */


    public void registrarSaida(ItemConsumo item, int quantidade, Localidade destino) {
 /*
        if (quantidade > this.getQuantidade()) {
            throw new SaldoInsuficienteException("Saida maior que o estoque");  // TODO - criar exceptions para esse tipo
        }
        this.setQuantidade(this.getQuantidade() - quantidade);

        MovimentacaoItemConsumo novaMovimentacao = new MovimentacaoItemConsumo(destino, item, TipoMovimentacao.SAIDA);
        this.movimentacaoRepositorio.salvar(novaMovimentacao);
*/
    }

    public void registrarEntrada(ItemConsumo item, int quantidade) {
/*
        if (quantidade <= 0) {
            throw new IllegalArgumentException("quantidade tem que ser positiva");  // TODO - criar exceptions para esse tipo
        }
        this.setQuantidade(this.getQuantidade() + quantidade);
        */
        MovimentacaoItemConsumo movimentacao = new MovimentacaoItemConsumo(Localidade.PATRIMONIO, item, TipoMovimentacao.ENTRADA); // POR QUE LOCALIDADE.PATRIMONI? ASSIM QUE UM ITEM CHEGAR ELE VAI DIRETO PRO PATRIMONI ANSTE DE SER DISTRIBUIDO

    }
}
