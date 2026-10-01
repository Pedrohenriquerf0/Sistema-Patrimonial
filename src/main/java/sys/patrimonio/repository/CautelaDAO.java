package sys.patrimonio.repository;

import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.model.Cautela;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.util.Processo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CautelaDAO implements CautelaRepositorio {
    private final ConnectionFactory connectionFactory;

    public CautelaDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Cautela buscarPorId(Long aLong) {
        String sql = """
                SELECT * FROM cautelas
                WHERE id = ?
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, aLong);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {


                String origem = resultSet.getString("origem");
                String destino = resultSet.getString("destino");

                Localidade origemLocal = origem != null && !origem.isBlank()
                        ? Localidade.valueOf(origem)
                        : null;

                Localidade destinoLocal = destino != null && !destino.isBlank()
                        ? Localidade.valueOf(destino)
                        : null;

                Cautela cautela = new Cautela(destinoLocal, resultSet.getLong("id"), resultSet.getString("observacoes"),origemLocal, resultSet.getString("tombo_item"), resultSet.getDate("criado_em").toLocalDate(), resultSet.getString("emissor"));


                return cautela;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

            while (resultSet.next()) {

                String origem = resultSet.getString("origem");
                String destino = resultSet.getString("destino");

                Localidade origemLocal = origem != null && !origem.isBlank()
                        ? Localidade.valueOf(origem)
                        : null;

                Localidade destinoLocal = destino != null && !destino.isBlank()
                        ? Localidade.valueOf(destino)
                        : null;

                Cautela novaCautela = new Cautela(destinoLocal, resultSet.getLong("id"), resultSet.getString("observacoes"),origemLocal, resultSet.getString("tombo_item"), resultSet.getDate("criado_em").toLocalDate(), resultSet.getString("emissor"));
                cautelaList.add(novaCautela);
            }
            return cautelaList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cautelaList;
    }

    @Override
    public void salvar(Cautela obj) {
        String sql = """
                INSERT INTO cautelas
                (tombo_item, destino, emissor, observacoes, origem)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, obj.getItemPatrimoniado().getTombo());
            statement.setString(2, String.valueOf(obj.getDestino()));
            statement.setString(3, obj.getEmissor());
            statement.setString(4, obj.getObservacoes().orElse(null));
            statement.setString(5, String.valueOf(obj.getOrigem()));

            statement.executeUpdate();

            System.out.println("Cautela criada com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String idCautela(Cautela obj) {
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
                long id = resultSet.getLong("id");
                LocalDate criadoEm = resultSet.getDate("criado_em").toLocalDate();
                return Processo.IDFormatada(id, criadoEm.getYear());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
