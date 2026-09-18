package sys.patrimonio.model;


import java.time.LocalDate;

public abstract class Item {
    private int quantidade;
    private LocalDate entrada;
    private String nome;
    private String descricao;
    private Localidade local;
    private Status status;
    private String categoria;
    private String foto;

    public Item(String nome, int quantidade, String descricao, Localidade local, String categoria, Status status, String caminhoFoto) {
        this.nome = nome;
        this.descricao = descricao;
        this.local = local;
        this.status = status;
        this.entrada = LocalDate.now();
        this.categoria = categoria;
        this.foto = caminhoFoto;
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setEntrada(LocalDate entrada) {
        this.entrada = entrada;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Localidade getLocal() {
        return local;
    }

    public void setLocal(Localidade local) {
        this.local = local;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getEntrada() {
        return entrada;
    }

    public String getCategoria() {
        return categoria;
    }

}
