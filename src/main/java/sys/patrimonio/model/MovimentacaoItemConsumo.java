package sys.patrimonio.model;

import sys.patrimonio.util.DataFormatada;
import sys.patrimonio.util.Processo;

import java.time.LocalDate;

public class MovimentacaoItemConsumo {
    private final Localidade destino;
    private final String emissor;
    private final String data;
    private final ItemConsumo itemConsumo;
    private final TipoMovimentacao tipoMovimentacao;
    private int quantidade;
    private long id;

    public MovimentacaoItemConsumo(Localidade destino,  ItemConsumo itemConsumo, int quantidade, TipoMovimentacao tipoMovimentacao) {
        this.destino = destino;
        this.emissor = Processo.emissorCautela();
        this.data = DataFormatada.dataAgora();
        this.itemConsumo = itemConsumo;
        this.quantidade = quantidade;
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public MovimentacaoItemConsumo(Localidade destino, String emissor, LocalDate data, ItemConsumo itemConsumo, TipoMovimentacao tipoMovimentacao, int quantidade, long id) {
        this.destino = destino;
        this.emissor = emissor;
        this.data = DataFormatada.dataFormat(data);
        this.itemConsumo = itemConsumo;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
