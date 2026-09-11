package sys.patrimonio.model;

import java.util.Objects;

public class ItemPatrimoniado extends Item{
    private String tombo;
    private String numeroSerie;

    public ItemPatrimoniado(String nome, String tombo, String numeroSerie, String descricao, Localidade local, String categoria, String caminhoFoto) {
        super(nome, 1,descricao, local, categoria, caminhoFoto);
        this.numeroSerie = numeroSerie;
        this.tombo = tombo;
    }

    public String getTombo() {
        return tombo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
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
