package sys.patrimonio.model;


import sys.patrimonio.util.DataFormatada;

public class ItemPatrimonio {
    private String entrada;
    private String nome;
    private String tombo;
    private String numeroSerie;
    private String descricao;
    private Departamento departamento;
    private int quantidade = 1;
    private Status status;
    private String categoria;
    private String foto;

    public ItemPatrimonio(String nome, String tombo, String numeroSerie, String descricao, Departamento departamento, String categoria, String caminhoFoto) {
        this.nome = nome;
        this.tombo = tombo;
        this.numeroSerie = numeroSerie;
        this.descricao = descricao;
        this.departamento = departamento;
        this.status = Status.NOVO;
        this.entrada = DataFormatada.dataAgora();
        this.categoria = categoria;
        this.foto = caminhoFoto;
    }

    public ItemPatrimonio(String nome, String descricao, Departamento departamento, int quantidade, String categoria, String caminhoFoto) {
        this.nome = nome;
        this.descricao = descricao;
        this.departamento = departamento;
        this.quantidade = quantidade;
        this.status = Status.NOVO;
        this.entrada = DataFormatada.dataAgora();
        this.categoria = categoria;
        this.foto = caminhoFoto;
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

    public String getTombo() {
        return tombo;
    }

    public void setTombo(String tombo) {
        this.tombo = tombo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public Status getStatus() {
        return status;
    }

    public String getEntrada() {
        return entrada;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "ItemsPatrimonio{" +
                "entrada='" + entrada + '\'' +
                ", nome='" + nome + '\'' +
                ", tombo='" + tombo + '\'' +
                ", numeroSerie='" + numeroSerie + '\'' +
                ", descricao='" + descricao + '\'' +
                ", departamento=" + departamento.getDepartamento() +
                ", quantidade=" + quantidade +
                ", status=" + status +
                ", categoria='" + categoria + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
