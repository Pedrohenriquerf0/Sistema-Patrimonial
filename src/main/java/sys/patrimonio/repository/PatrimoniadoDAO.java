package sys.patrimonio.repository;


import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.exceptions.PatrimonioExistenteException;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.util.Convert;

import java.io.IOException;
import java.sql.*;

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

        } catch (SQLIntegrityConstraintViolationException e) {
           throw new PatrimonioExistenteException("Patrimonio ja cadastrado");
        } catch (SQLException e) {
            System.out.println("Erro no salvamento");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(objeto.toString() + "salvo com sucesso"); // TODO vai ser criado uma metodo que chama uma telinha de aviso
    }

    @Override
    public void listar(ItemPatrimoniado objeto) {

    }

    @Override
    public void atualizar(ItemPatrimoniado objeto) {

    }

    @Override
    public ItemPatrimoniado buscarPorId(String s) {
        return null;
    }

    @Override
    public void excluir(String s) {

    }
}

