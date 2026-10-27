package sys.patrimonio.repository;

import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.exceptions.ConsultaException;
import sys.patrimonio.exceptions.PersistenciaException;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.util.ConverterArquivos;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsumoDAO implements ConsumoRepositorio {
    private final ConnectionFactory connectionFactory;

    public ConsumoDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void salvar(ItemConsumo objeto) {
        String sql = """
                INSERT INTO item_consumo
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
            statement.setBytes(8, ConverterArquivos.pathsToBytes(objeto.getFoto()));

            statement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() == 1062) {
                throw new PersistenciaException(
                        "Já existe um item de consumo com um valor único duplicado.",
                        e
                );
            }

            throw new PersistenciaException(
                    "Não foi possível salvar o item de consumo devido a uma violação de integridade.",
                    e
            );
        } catch (SQLException | IOException e) {
            throw new PersistenciaException("Não foi possível salvar o item de consumo.", e);
        }
    }

    @Override
    public List<ItemConsumo> listar() {
        List<ItemConsumo> consumoList = new ArrayList<>();
        String sql = """
                SELECT * FROM item_consumo
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String caminhoFoto = ConverterArquivos.bytesToPaths(
                        resultSet.getBytes("foto"), resultSet.getString("nome"));

                ItemConsumo itemConsumo = new ItemConsumo(
                        resultSet.getString("nome"),
                        resultSet.getInt("quantidade"),
                        resultSet.getString("descricao"),
                        Localidade.valueOf(resultSet.getString("localidade")),
                        resultSet.getString("categoria"),
                        Status.valueOf(resultSet.getString("status")),
                        caminhoFoto
                );
                itemConsumo.setId(resultSet.getLong("id"));
                consumoList.add(itemConsumo);
            }

            return consumoList;
        } catch (SQLException | IOException e) {
            throw new ConsultaException("Não foi possível listar os itens de consumo.", e);
        }
    }

    @Override
    public void atualizar(ItemConsumo objeto) {
        String sql = """
                UPDATE item_consumo
                SET nome = ?,
                    quantidade = ?,
                    descricao = ?,
                    localidade = ?,
                    status = ?,
                    categoria = ?,
                    foto = ?
                WHERE id = ?
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, objeto.getNome());
            statement.setInt(2, objeto.getQuantidade());
            statement.setString(3, objeto.getDescricao());
            statement.setString(4, String.valueOf(objeto.getLocal()));
            statement.setString(5, String.valueOf(objeto.getStatus()));
            statement.setString(6, objeto.getCategoria());
            statement.setBytes(7, ConverterArquivos.pathsToBytes(objeto.getFoto()));
            statement.setLong(8, objeto.getId());

            statement.executeUpdate();

        } catch (SQLException | IOException e) {
            throw new PersistenciaException("Não foi possível atualizar o item de consumo.", e);
        }

    }



    @Override
    public ItemConsumo buscarPorId(Long aLong) {
        String sql = """
                SELECT * FROM item_consumo
                WHERE id = ?
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, aLong);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String caminhoFoto = ConverterArquivos.bytesToPaths(
                            resultSet.getBytes("foto"), resultSet.getString("nome"));

                    ItemConsumo itemConsumo = new ItemConsumo(
                            resultSet.getString("nome"),
                            resultSet.getInt("quantidade"),
                            resultSet.getString("descricao"),
                            Localidade.valueOf(resultSet.getString("localidade")),
                            resultSet.getString("categoria"),
                            Status.valueOf(resultSet.getString("status")),
                            caminhoFoto
                    );
                    itemConsumo.setId(resultSet.getLong("id"));
                    return itemConsumo;
                }
            }
        } catch (SQLException | IOException e) {
            throw new ConsultaException("Não foi possível buscar o item de consumo pelo ID informado.", e);
        }

        return null;
    }

    @Override
    public List<ItemConsumo> buscarPorNome(String nome) {
        List<ItemConsumo> consumoList = new ArrayList<>();
        String sql = """
                SELECT * FROM item_consumo
                WHERE nome LIKE ?
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + nome + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String caminhoFoto = ConverterArquivos.bytesToPaths(
                            resultSet.getBytes("foto"), resultSet.getString("nome"));

                    ItemConsumo itemConsumo = new ItemConsumo(
                            resultSet.getString("nome"),
                            resultSet.getInt("quantidade"),
                            resultSet.getString("descricao"),
                            Localidade.valueOf(resultSet.getString("localidade")),
                            resultSet.getString("categoria"),
                            Status.valueOf(resultSet.getString("status")),
                            caminhoFoto
                    );
                    itemConsumo.setEntrada(resultSet.getDate("data_entrada").toLocalDate());
                    itemConsumo.setId(resultSet.getLong("id"));
                    consumoList.add(itemConsumo);
                }
            }
            return consumoList;
        } catch (SQLException | IOException e) {
            throw new ConsultaException("Não foi possível buscar itens de consumo pelo nome informado.", e);
        }
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

        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível excluir o item de consumo.", e);
        }
    }

    @Override
    public void atualizarQuantidade(Connection connection, ItemConsumo objeto) throws SQLException, IOException {
        String sql = """
            UPDATE item_consumo
            SET quantidade = ?
            WHERE id = ?
            """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, objeto.getQuantidade());
            statement.setLong(2, objeto.getId());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas != 1) {
                throw new SQLException(
                        "O item de consumo não foi encontrado para atualização."
                );
            }
        }
    }
}

