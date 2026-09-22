package sys.patrimonio.repository;


import java.util.List;

public interface RepositorioSomenteInsercao<T, ID> {
    T buscarPorId(ID id);

    List<T> listarTodos();

    void salvar(T obj);
}
