package sys.patrimonio.repository;

import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.model.Cautela;
import sys.patrimonio.util.Convert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CautelaDAO implements CautelaRepositorio {
    private final ConnectionFactory connectionFactory;

    public CautelaDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Cautela buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<Cautela> listarTodos() {
        List<Cautela> cautelaList = new ArrayList<>();
        String sql = """
                SELECT * FROM cautelas
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()){
                String caminhoFoto = Convert.bytesToPaths(resultSet.getBytes("foto"), resultSet.getString("nome"));

                //Cautela novaCautela = new Cautela()
            }
            return cautelaList;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return cautelaList;
    }

    @Override
    public void salvar(Cautela obj) {
        String sql = """
                INSERT INTO cautelas
                (tombo_item, destino, emissor, observacoes)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, obj.getItemPatrimoniado().getTombo());
            statement.setString(2, String.valueOf(obj.getDestino()));
            statement.setString(3, obj.getEmissor());
            statement.setString(4, obj.getObservacoes().orElse(null));

            statement.executeUpdate();

            System.out.println("Cautela criada com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro na criacao da cautela");
        }
    }

    @Override
    public Long idCautela(Cautela obj) {
        String sql = """
                SELECT * FROM cautelas
                WHERE tombo_item = ?
                ORDER BY criado_em DESC
                LIMIT 1;
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, obj.getItemPatrimoniado().getTombo());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pegar id da cautela");
        }
        return null;
    }
}
