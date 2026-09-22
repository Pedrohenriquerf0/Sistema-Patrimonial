package sys.patrimonio.model;

import org.checkerframework.checker.nullness.qual.Nullable;
import sys.patrimonio.util.DataFormatada;
import sys.patrimonio.util.Processo;

import java.util.Optional;

public class Cautela {
    private Localidade destino;
    private String emissor;
    private String data;
    private ItemPatrimoniado itemPatrimoniado;
    private String observacoes;
    private String id;

    public Cautela(ItemPatrimoniado itemPatrimoniado, Localidade destino, @Nullable String obsevacoes) {
        this.itemPatrimoniado = itemPatrimoniado;
        this.data = DataFormatada.dataAgora();
        this.emissor = Processo.emissorCautela();
        this.destino = destino;
        this.observacoes = obsevacoes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Optional<String> getObservacoes() {
        return Optional.ofNullable(observacoes);
    }

}
