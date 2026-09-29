package sys.patrimonio.repository;

import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.exceptions.PatrimonioExistenteException;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.util.Convert;

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
    public List<ItemConsumo> listar() {// TODO implementação para inventario ou relatorio

        List<ItemConsumo> consumoList = new ArrayList<>();
        String sql = """
                SELECT * FROM item_consumo
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String caminhoFoto = Convert.bytesToPaths(resultSet.getBytes("foto"), resultSet.getString("nome"));

                ItemConsumo itemConsumo =
                        new ItemConsumo(
                                resultSet.getString("nome"),
                                resultSet.getInt("quantidade"),
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
                itemConsumo.setId(resultSet.getLong("id"));
                consumoList.add(itemConsumo);
            }

            return consumoList;
        } catch (SQLException e) {
            System.out.println("Erro ao listar items");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void atualizar(ItemConsumo objeto) {
        String sql = """
                UPDATE item_consumo
                SET nome = ?,
                    quantidade = ?,
                    descricao = ?,
                    localidade= ?,
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
            statement.setBytes(7, Convert.pathsToBytes(objeto.getFoto()));
            statement.setLong(8, objeto.getId());

            statement.executeUpdate();

            System.out.println("Item atualizado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar item");
        } catch (IOException e) {
            e.printStackTrace();
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

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String caminhoFoto = Convert.bytesToPaths(resultSet.getBytes("foto"), resultSet.getString("nome"));

                ItemConsumo itemConsumo =
                        new ItemConsumo(
                                resultSet.getString("nome"),
                                resultSet.getInt("quantidade"),
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
                itemConsumo.setId(resultSet.getLong("id"));

                return itemConsumo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ItemConsumo buscarPorNome(String nome) {
        String sql = """
            SELECT * FROM item_consumo
            WHERE nome = ?
            """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nome);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String caminhoFoto = Convert.bytesToPaths(resultSet.getBytes("foto"), resultSet.getString("nome"));

                ItemConsumo itemConsumo =
                        new ItemConsumo(
                                resultSet.getString("nome"),
                                resultSet.getInt("quantidade"),
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
                itemConsumo.setId(resultSet.getLong("id"));

                return itemConsumo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
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
