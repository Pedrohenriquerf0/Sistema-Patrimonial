package sys.patrimonio.repository;

import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.exceptions.ConsultaException;
import sys.patrimonio.exceptions.PatrimonioExistenteException;
import sys.patrimonio.exceptions.PersistenciaException;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.util.Converter;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatrimoniadoDAO implements PatrimoniadoRepositorio {
    private final ConnectionFactory connectionFactory;

    public PatrimoniadoDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void salvar(ItemPatrimoniado objeto) {
        String sql = """
                INSERT INTO item_patrimoniado
                (tombo, numero_serie, data_entrada, nome, descricao, localidade, status, categoria, foto)
                VALUES (?,?,?,?,?,?,?,?,?)
                """;
        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, objeto.getTombo());
            statement.setString(2, objeto.getNumeroSerie());
            statement.setDate(3, Date.valueOf(objeto.getEntrada()));
            statement.setString(4, objeto.getNome());
            statement.setString(5, objeto.getDescricao());
            statement.setString(6, String.valueOf(objeto.getLocal()));
            statement.setString(7, String.valueOf(objeto.getStatus()));
            statement.setString(8, objeto.getCategoria());
            statement.setBytes(9, Converter.pathsToBytes(objeto.getFoto()));

            statement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() == 1062) {
                throw new PatrimonioExistenteException(
                        "Já existe um patrimônio com um valor único duplicado.", e
                );
            }

            throw new PersistenciaException(
                    "Não foi possível salvar o patrimônio.", e
            );
        } catch (SQLException | IOException e) {
            throw new PersistenciaException("Não foi possível salvar o patrimônio.", e);
        }
    }

    @Override
    public List<ItemPatrimoniado> listar() { // TODO implementação para inventario ou relatorio
        List<ItemPatrimoniado> patrimoniadoList = new ArrayList<>();

        String sql = """
                SELECT * FROM item_patrimoniado
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String caminhoFoto = Converter.bytesToPaths(resultSet.getBytes("foto"), resultSet.getString("tombo"));

                ItemPatrimoniado itemPatrimoniado =
                        new ItemPatrimoniado(
                                resultSet.getString("nome"),
                                resultSet.getString("tombo"),
                                resultSet.getString("numero_serie"),
                                resultSet.getString("descricao"),
                                Localidade.valueOf(
                                        resultSet.getString("localidade")
                                ),
                                resultSet.getString("categoria"),
                                Status.valueOf(
                                        resultSet.getString("status")
                                ),
                                caminhoFoto
                        );
                itemPatrimoniado.setEntrada(resultSet.getDate("data_entrada").toLocalDate());
                patrimoniadoList.add(itemPatrimoniado);
            }

            return patrimoniadoList;
        } catch (SQLException | IOException e) {
            throw new ConsultaException("Não foi possível listar os patrimônios.", e);
        }
    }

    @Override
    public void atualizar(ItemPatrimoniado objeto) {
        String sql = """
                UPDATE item_patrimoniado
                SET nome = ?,
                    descricao = ?,
                    localidade= ?,
                    status = ?,
                    categoria = ?,
                    foto = ?
                WHERE tombo = ?
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, objeto.getNome());
            statement.setString(2, objeto.getDescricao());
            statement.setString(3, String.valueOf(objeto.getLocal()));
            statement.setString(4, String.valueOf(objeto.getStatus()));
            statement.setString(5, objeto.getCategoria());
            statement.setBytes(6, Converter.pathsToBytes(objeto.getFoto()));
            statement.setString(7, objeto.getTombo());

            statement.executeUpdate();

        } catch (SQLException | IOException e) {
            throw new PersistenciaException("Não foi possível atualizar o patrimônio.", e);
        }
    }

    @Override
    public ItemPatrimoniado buscarPorId(String tombo) {

        String sql = """
                SELECT * FROM item_patrimoniado
                WHERE tombo = ?
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, tombo);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String caminhoFoto = Converter.bytesToPaths(resultSet.getBytes("foto"), resultSet.getString("tombo"));

                ItemPatrimoniado itemPatrimoniado =
                        new ItemPatrimoniado(
                                resultSet.getString("nome"),
                                resultSet.getString("tombo"),
                                resultSet.getString("numero_serie"),
                                resultSet.getString("descricao"),
                                Localidade.valueOf(
                                        resultSet.getString("localidade")
                                ),
                                resultSet.getString("categoria"),
                                Status.valueOf(
                                        resultSet.getString("status")
                                ),
                                caminhoFoto
                        );
                itemPatrimoniado.setEntrada(resultSet.getDate("data_entrada").toLocalDate());
                return itemPatrimoniado;
            }
        } catch (SQLException | IOException e) {
            throw new ConsultaException("Não foi possível buscar o patrimônio pelo tombo informado.", e);
        }
        return null;
    }

    @Override
    public List<ItemPatrimoniado> buscarPorNome(String nome) {
        List<ItemPatrimoniado> patrimoniadoList = new ArrayList<>();
        String sql = """
                SELECT * FROM item_patrimoniado
                WHERE nome LIKE ?
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + nome + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String caminhoFoto = Converter.bytesToPaths(resultSet.getBytes("foto"), resultSet.getString("tombo"));

                ItemPatrimoniado itemPatrimoniado =
                        new ItemPatrimoniado(
                                resultSet.getString("nome"),
                                resultSet.getString("tombo"),
                                resultSet.getString("numero_serie"),
                                resultSet.getString("descricao"),
                                Localidade.valueOf(
                                        resultSet.getString("localidade")
                                ),
                                resultSet.getString("categoria"),
                                Status.valueOf(
                                        resultSet.getString("status")
                                ),
                                caminhoFoto
                        );
                itemPatrimoniado.setEntrada(resultSet.getDate("data_entrada").toLocalDate());
                patrimoniadoList.add(itemPatrimoniado);
            }
            return patrimoniadoList;
        } catch (SQLException | IOException e) {
            throw new ConsultaException("Não foi possível buscar patrimônios pelo nome informado.", e);
        }
    }

    @Override
    public void excluir(String tombo) {
        String sql = """
                DELETE FROM item_patrimoniado
                WHERE tombo = ?
                LIMIT 1
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, tombo);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível excluir o patrimônio.", e);
        }
    }

    @Override
    public ItemPatrimoniado buscarPorNS(String numeroSerie) {
        String sql = """
                SELECT * FROM item_patrimoniado
                WHERE numero_serie = ?
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, numeroSerie);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String caminhoFoto = Converter.bytesToPaths(resultSet.getBytes("foto"), resultSet.getString("tombo"));

                ItemPatrimoniado itemPatrimoniado =
                        new ItemPatrimoniado(
                                resultSet.getString("nome"),
                                resultSet.getString("tombo"),
                                resultSet.getString("numero_serie"),
                                resultSet.getString("descricao"),
                                Localidade.valueOf(
                                        resultSet.getString("localidade")
                                ),
                                resultSet.getString("categoria"),
                                Status.valueOf(
                                        resultSet.getString("status")
                                ),
                                caminhoFoto
                        );
                itemPatrimoniado.setEntrada(resultSet.getDate("data_entrada").toLocalDate());

                return itemPatrimoniado;
            }
        } catch (SQLException | IOException e) {
            throw new ConsultaException("Não foi possível buscar o patrimônio pelo número de série informado.", e);
        }
        return null;
    }
}
