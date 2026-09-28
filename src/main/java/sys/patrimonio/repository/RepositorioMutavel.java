package sys.patrimonio.repository;

public interface RepositorioMutavel<T, ID>{
    void salvar(T objeto);
    void listar();
    void atualizar(T objeto);
    T buscarPorId(ID id);
    T buscarPorNome(String nome);
    void excluir(ID id);

}
