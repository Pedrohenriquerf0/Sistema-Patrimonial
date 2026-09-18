package sys.patrimonio.model;

import java.util.Objects;

public class ItemConsumo extends Item {

    private Long id;

    public ItemConsumo(String nome, int quantidade, String descricao, Localidade local, String categoria,Status status, String caminhoFoto) {
        super(nome, quantidade, descricao, local, categoria, status, caminhoFoto);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void darBaixa(int quantidade){
        if(quantidade > this.getQuantidade()){
            throw new IllegalStateException("Saida maior que o estoque");
        }
        this.setQuantidade(this.getQuantidade() - quantidade);
    }

    public void reporEstoque(int quantidade){
        if(quantidade <= 0){
            throw new IllegalArgumentException("quantidade tem que ser positiva");
        }
        this.setQuantidade(this.getQuantidade() + quantidade);
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
