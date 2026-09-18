package sys.patrimonio.repository;

public interface Repositorio <T, ID>{
    void salvar(T objeto);
    void listar(T objeto);
    void atualizar(T objeto);
    void excluir(ID id);

}
