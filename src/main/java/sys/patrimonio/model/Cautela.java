package sys.patrimonio.model;

import org.checkerframework.checker.nullness.qual.Nullable;
import sys.patrimonio.util.DataFormatada;
import sys.patrimonio.util.Processo;

import java.time.LocalDate;
import java.util.Optional;

public class Cautela {
    private Localidade destino;
    private String emissor;
    private String data;
    private ItemPatrimoniado itemPatrimoniado;
    private Localidade origem;
    private String observacoes;
    private String id;

    public Cautela(ItemPatrimoniado itemPatrimoniado, Localidade destino, Localidade origem, @Nullable String obsevacoes) {
        this.itemPatrimoniado = itemPatrimoniado;
        this.emissor = Processo.emissorCautela();
        this.data = DataFormatada.dataAgora();
        this.destino = destino;
        this.origem = origem;
        this.observacoes = obsevacoes;
    }

    public Cautela(Localidade destino, long id, @Nullable String observacoes, Localidade origem, String tombo_item, LocalDate data, String emissor) {
        this.destino = destino;
        this.id = Processo.IDFormatada(id, data.getYear());
        this.observacoes = observacoes;
        this.origem = origem;
        this.itemPatrimoniado = new ItemPatrimoniado(tombo_item); // TODO QUANDO CHAMAR A BUSCA O LISTA TAMBEM CHAMA BUSCA POR TOMBO DO PATRIMONIADO
        this.data = DataFormatada.dataFormat(data);
        this.emissor = emissor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Localidade getOrigem() {
        return origem;
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
