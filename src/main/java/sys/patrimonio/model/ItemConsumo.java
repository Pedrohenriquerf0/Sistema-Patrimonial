package sys.patrimonio.model;

import java.util.Objects;

public class ItemConsumo extends Item {

    private Long id;

    public ItemConsumo(String nome, int quantidade, String descricao, Localidade local, String categoria, String caminhoFoto) {
        super(nome, quantidade, descricao, local, categoria, caminhoFoto);

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ItemConsumo other = (ItemConsumo) object;
        if (id == null || other.id == null) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
