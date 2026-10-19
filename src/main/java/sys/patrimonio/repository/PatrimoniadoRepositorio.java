package sys.patrimonio.repository;

import sys.patrimonio.model.ItemPatrimoniado;

import java.sql.Connection;
import java.sql.SQLException;

public interface PatrimoniadoRepositorio extends RepositorioMutavel<ItemPatrimoniado, String>{
     ItemPatrimoniado buscarPorNS(String numeroSerie);

    void atualizarLocal(Connection connection, ItemPatrimoniado objeto) throws SQLException;
}
