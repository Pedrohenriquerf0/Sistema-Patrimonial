package sys.patrimonio.model;

import sys.patrimonio.util.DataFormatada;
import sys.patrimonio.util.Processo;

public class MovimentacaoItemConsumo {
    private final Localidade destino;
    private final String emissor;
    private final String data;
    private final ItemConsumo itemConsumo;
    private final TipoMovimentacao tipoMovimentacao;

    public MovimentacaoItemConsumo(Localidade destino,  ItemConsumo itemConsumo, TipoMovimentacao tipoMovimentacao) {
        this.destino = destino;
        this.emissor = Processo.emissorCautela();
        this.data = DataFormatada.dataAgora();
        this.itemConsumo = itemConsumo;
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Localidade getDestino() {
        return destino;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public ItemConsumo getItemConsumo() {
        return itemConsumo;
    }

    public String getData() {
        return data;
    }

    public String getEmissor() {
        return emissor;
    }
}
