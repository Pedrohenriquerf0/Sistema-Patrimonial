package sys.patrimonio.repository;


import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.exceptions.PatrimonioExistenteException;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.util.Convert;

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
            statement.setBytes(9, Convert.pathsToBytes(objeto.getFoto()));

            statement.executeUpdate();

            System.out.println(objeto.toString() + "salvo com sucesso"); // TODO vai ser criado uma metodo que chama uma telinha de aviso
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new PatrimonioExistenteException("Patrimonio ja cadastrado");
        } catch (SQLException e) {
            System.out.println("Erro no salvamento");
        } catch (IOException e) {
            e.printStackTrace();
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
                String caminhoFoto = Convert.bytesToPaths(resultSet.getBytes("foto"), resultSet.getString("tombo"));

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
                patrimoniadoList.add(itemPatrimoniado);
            }

            return patrimoniadoList;
        } catch (SQLException e) {
            System.out.println("Erro ao listar items");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
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
            statement.setBytes(6, Convert.pathsToBytes(objeto.getFoto()));
            statement.setString(7, objeto.getTombo());

            statement.executeUpdate();

            System.out.println("Item atualizado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar item");
        } catch (IOException e) {
            e.printStackTrace();
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
                String caminhoFoto = Convert.bytesToPaths(resultSet.getBytes("foto"), resultSet.getString("tombo"));

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

                return itemPatrimoniado;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ItemPatrimoniado> buscarPorNome(String nome) {
        List<ItemPatrimoniado> patrimoniadoList =new ArrayList<>();
        String sql = """
            SELECT * FROM item_patrimoniado
            WHERE nome LIKE ?
            """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nome);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String caminhoFoto = Convert.bytesToPaths(resultSet.getBytes("foto"), resultSet.getString("tombo"));

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
                patrimoniadoList.add(itemPatrimoniado);
            }
            return patrimoniadoList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
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

            System.out.println("Excluido com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro no deletamento ");
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
                String caminhoFoto = Convert.bytesToPaths(resultSet.getBytes("foto"), resultSet.getString("tombo"));

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

                return itemPatrimoniado;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}

