package sys.patrimonio.repository;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RepositorioSomenteInsercao<T, ID> {
    T buscarPorId(ID id);

    List<T> listarTodos();

    void salvar(T obj);

    List<T> buscarUltimasInsercoes() throws SQLException;

    Map<String, String> listarTotalData() throws SQLException;
}
