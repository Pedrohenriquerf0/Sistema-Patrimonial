package sys.patrimonio.repository;

import java.util.List;

public interface RepositorioMutavel<T, ID>{
    void salvar(T objeto);
    List<T> listar();
    void atualizar(T objeto);
    T buscarPorId(ID id);
    List<T> buscarPorNome(String nome);
    void excluir(ID id);

}
