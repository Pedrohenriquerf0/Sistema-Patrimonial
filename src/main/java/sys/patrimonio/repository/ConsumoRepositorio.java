package sys.patrimonio.repository;

import sys.patrimonio.model.ItemConsumo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public interface ConsumoRepositorio extends RepositorioMutavel<ItemConsumo, Long> {
    void atualizarQuantidade(Connection connection, ItemConsumo objeto) throws SQLException, IOException;
}
