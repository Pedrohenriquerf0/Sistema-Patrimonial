package sys.patrimonio.repository;

import sys.patrimonio.config.AppContext;
import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.model.Cautela;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.util.Processo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CautelaDAO implements CautelaRepositorio {
    private final ConnectionFactory connectionFactory;

    public CautelaDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Cautela buscarPorId(Long aLong) {
        PatrimoniadoRepositorio patrimoniadoRepositorio = AppContext.getPatrimoniadoRepositorio();
        String sql = """
                SELECT * FROM cautelas
                WHERE id = ?
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, aLong);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Localidade origemLocal = Optional.ofNullable(resultSet.getString("origem"))
                        .filter(s -> !s.isBlank())
                        .map(Localidade::valueOf)
                        .orElse(null);


                Localidade destinoLocal = Optional.ofNullable(resultSet.getString("destino"))
                        .filter(s -> !s.isBlank())
                        .map(Localidade::valueOf)
                        .orElse(null);


                ItemPatrimoniado itemPatrimoniado = patrimoniadoRepositorio.buscarPorId(resultSet.getString("tombo_item"));

                Cautela cautela = new Cautela(destinoLocal, resultSet.getLong("id"), resultSet.getString("observacoes"), origemLocal, itemPatrimoniado, resultSet.getDate("criado_em").toLocalDate(), resultSet.getString("emissor"));


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
        PatrimoniadoRepositorio patrimoniadoRepositorio = AppContext.getPatrimoniadoRepositorio();
        String sql = """
                SELECT * FROM cautelas
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Localidade origemLocal = Optional.ofNullable(resultSet.getString("origem"))
                        .filter(s -> !s.isBlank())
                        .map(Localidade::valueOf)
                        .orElse(null);


                Localidade destinoLocal = Optional.ofNullable(resultSet.getString("destino"))
                        .filter(s -> !s.isBlank())
                        .map(Localidade::valueOf)
                        .orElse(null);

                ItemPatrimoniado itemPatrimoniado = patrimoniadoRepositorio.buscarPorId(resultSet.getString("tombo_item"));

                Cautela novaCautela = new Cautela(destinoLocal, resultSet.getLong("id"),
                        resultSet.getString("observacoes"), origemLocal,itemPatrimoniado
                        , resultSet.getDate("criado_em").toLocalDate(), resultSet.getString("emissor"));

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
                RETURNING id, criado_em
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, obj.getItemPatrimoniado().getTombo());
            statement.setString(2, String.valueOf(obj.getDestino()));
            statement.setString(3, obj.getEmissor());
            statement.setString(4, obj.getObservacoes().orElse(null));
            statement.setString(5, String.valueOf(obj.getOrigem()));

            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                long id = resultado.getLong("id");
                LocalDate criadoEm = resultado.getDate("criado_em").toLocalDate();
                obj.setId(Processo.IDFormatada(id, criadoEm.getYear()));
            }

            System.out.println("Cautela criada com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
