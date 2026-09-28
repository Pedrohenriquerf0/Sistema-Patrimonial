package sys.patrimonio.repository;

import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.exceptions.PatrimonioExistenteException;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.util.Convert;

import java.io.IOException;
import java.sql.*;

public class ConsumoDAO implements ConsumoRepositorio {
    private final ConnectionFactory connectionFactory;

    public ConsumoDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void salvar(ItemConsumo objeto) {
        String sql = """
                INSERT INTO  item_consumo
                (quantidade, data_entrada, nome, descricao, localidade, status, categoria, foto)
                VALUES (?,?,?,?,?,?,?,?)
                """;
        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setInt(1, objeto.getQuantidade());
            statement.setDate(2, Date.valueOf(objeto.getEntrada()));
            statement.setString(3, objeto.getNome());
            statement.setString(4, objeto.getDescricao());
            statement.setString(5, String.valueOf(objeto.getLocal()));
            statement.setString(6, String.valueOf(objeto.getStatus()));
            statement.setString(7, objeto.getCategoria());
            statement.setBytes(8, Convert.pathsToBytes(objeto.getFoto()));

            statement.executeUpdate();

            System.out.println("salvo com sucesso"); // TODO vai ser criado uma metodo que chama uma telinha de aviso
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new PatrimonioExistenteException("Patrimonio ja cadastrado");
        } catch (SQLException e) {
            System.out.println("Erro no salvamento");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listar() {

    }

    @Override
    public void atualizar(ItemConsumo objeto) {

    }

    @Override
    public ItemConsumo buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public ItemConsumo buscarPorNome(String nome) {
        return null;
    }

    @Override
    public void excluir(Long aLong) {
        String sql = """
                DELETE FROM item_consumo
                WHERE id = ?
                LIMIT 1
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, aLong);
            statement.executeUpdate();

            System.out.println("Excluido com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro no deletamento ");
        }
    }

}
