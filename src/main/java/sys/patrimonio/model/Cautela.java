package sys.patrimonio.model;

import sys.patrimonio.util.DataFormatada;
import sys.patrimonio.util.CautelaProcesso;

public class Cautela {
    private Localidade destino;
    private String emissor;
    private String data;
    private ItemPatrimonio itemPatrimonio;
    private String obsevacoes;


    public Cautela(ItemPatrimonio itemPatrimonio, Localidade destino) {
        this.itemPatrimonio = itemPatrimonio;
        this.data = DataFormatada.dataAgora();
        this.emissor = CautelaProcesso.emissorCautela();
        this.destino = destino;
        this.obsevacoes = "-";

    }

    public Cautela(ItemPatrimonio itemPatrimonio, Localidade destino, String obsevacoes) {
        this.itemPatrimonio = itemPatrimonio;
        this.data = DataFormatada.dataAgora();
        this.emissor = CautelaProcesso.emissorCautela();
        this.destino = destino;
        this.obsevacoes = obsevacoes;
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

    public ItemPatrimonio getItemPatrimonio() {
        return itemPatrimonio;
    }

    public String getObsevacoes() {
        return obsevacoes;
    }

}
