package sys.patrimonio.model;

import java.util.Objects;

public class ItemPatrimoniado extends Item{
    private String tombo;
    private String numeroSerie;

    public ItemPatrimoniado(String nome, String tombo, String numeroSerie, String descricao, Localidade local, String categoria, Status status, String caminhoFoto) {
        super(nome, 1,descricao, local, categoria, status,  caminhoFoto);
        this.numeroSerie = numeroSerie;
        this.tombo = tombo;
    }

    public ItemPatrimoniado(String tombo) {
        super(null, 1, null, null, null, null, null);
        this.tombo = tombo;
        this.numeroSerie = null;
    }

    public String getTombo() {
        return tombo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setTombo(String tombo) {
        this.tombo = tombo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        ItemPatrimoniado other = (ItemPatrimoniado) obj;
        return tombo.equals(other.tombo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tombo);
    }
}
