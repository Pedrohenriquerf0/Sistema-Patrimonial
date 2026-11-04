package sys.patrimonio.repository;

import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface RepositorioMutavel<T, ID>{
    void salvar(T objeto);
    void atualizar(T objeto);
    T buscarPorId(ID id);
    List<T> buscarPorNome(String nome);
    void excluir(ID id);
    List<T> relatorio(LocalDate dataInicial, LocalDate dataFinal, Status status, Localidade localidade) throws SQLException, IOException;
    Integer listarTotal() throws SQLException;

}
