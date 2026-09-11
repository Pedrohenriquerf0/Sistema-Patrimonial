package sys.patrimonio.model;

import sys.patrimonio.util.DataFormatada;
import sys.patrimonio.util.CautelaProcesso;

public class Cautela {
    private Localidade destino;
    private String emissor;
    private String data;
    private ItemPatrimoniado itemPatrimoniado;
    private String observacoes;


    public Cautela(ItemPatrimoniado itemPatrimoniado, Localidade destino) {
        this.itemPatrimoniado = itemPatrimoniado;
        this.data = DataFormatada.dataAgora();
        this.emissor = CautelaProcesso.emissorCautela();
        this.destino = destino;
        this.observacoes = "-";

    }

    public Cautela(ItemPatrimoniado itemPatrimoniado, Localidade destino, String obsevacoes) {
        this.itemPatrimoniado = itemPatrimoniado;
        this.data = DataFormatada.dataAgora();
        this.emissor = CautelaProcesso.emissorCautela();
        this.destino = destino;
        this.observacoes = obsevacoes;
    }

    public Localidade getDestino() {
        return destino;
    }

    public String getEmissor() {
        return emissor;
    }

    public String getData() {
        return data;
    }

    public ItemPatrimoniado getItemPatrimoniado() {
        return itemPatrimoniado;
    }

    public String getObservacoes() {
        return observacoes;
    }

}
