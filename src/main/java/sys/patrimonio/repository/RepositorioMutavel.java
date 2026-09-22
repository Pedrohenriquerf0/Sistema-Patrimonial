package sys.patrimonio.repository;

public interface RepositorioMutavel<T, ID>{
    void salvar(T objeto);
    void listar(T objeto);
    void atualizar(T objeto);
    T buscarPorId(ID id);
    void excluir(ID id);

}
