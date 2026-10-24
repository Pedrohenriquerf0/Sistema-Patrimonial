package sys.patrimonio.repository;

import sys.patrimonio.config.AppContext;
import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.exceptions.ConsultaException;
import sys.patrimonio.exceptions.PersistenciaException;
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
    public Cautela buscarPorId(Long id) {
        PatrimoniadoRepositorio patrimoniadoRepositorio = AppContext.getPatrimoniadoRepositorio();
        String sql = """
                SELECT * FROM cautelas
                WHERE id = ?
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Localidade origemLocal = Optional.ofNullable(resultSet.getString("origem"))
                            .filter(s -> !s.isBlank())
                            .map(Localidade::valueOf)
                            .orElse(null);

                    Localidade destinoLocal = Optional.ofNullable(resultSet.getString("destino"))
                            .filter(s -> !s.isBlank())
                            .map(Localidade::valueOf)
                            .orElse(null);

                    ItemPatrimoniado itemPatrimoniado =
                            patrimoniadoRepositorio.buscarPorId(resultSet.getString("tombo_item"));

                    return new Cautela(
                            destinoLocal,
                            resultSet.getLong("id"),
                            resultSet.getString("observacoes"),
                            origemLocal,
                            itemPatrimoniado,
                            resultSet.getDate("criado_em").toLocalDate(),
                            resultSet.getString("emissor")
                    );
                }
            }
        } catch (SQLException e) {
            throw new ConsultaException(
                    "Não foi possível buscar a cautela pelo ID informado.",
                    e
            );
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

                ItemPatrimoniado itemPatrimoniado =
                        patrimoniadoRepositorio.buscarPorId(resultSet.getString("tombo_item"));

                Cautela novaCautela = new Cautela(
                        destinoLocal,
                        resultSet.getLong("id"),
                        resultSet.getString("observacoes"),
                        origemLocal,
                        itemPatrimoniado,
                        resultSet.getDate("criado_em").toLocalDate(),
                        resultSet.getString("emissor")
                );

                cautelaList.add(novaCautela);
            }

            return cautelaList;
        } catch (SQLException e) {
            throw new ConsultaException(
                    "Não foi possível listar as cautelas.",
                    e
            );
        }
    }

    @Override
    public void salvar(Cautela obj) {
        PatrimoniadoRepositorio patrimoniadoRepositorio =
                AppContext.getPatrimoniadoRepositorio();

        String sql = """
                INSERT INTO cautelas
                    (tombo_item, destino, emissor, observacoes, origem)
                VALUES (?, ?, ?, ?, ?)
                RETURNING id, criado_em
                """;

        try (Connection connection = this.connectionFactory.getConnection()) {
            connection.setAutoCommit(false);

            try {
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, obj.getItemPatrimoniado().getTombo());
                    statement.setString(2, String.valueOf(obj.getDestino()));
                    statement.setString(3, obj.getEmissor());
                    statement.setString(4, obj.getObservacoes().orElse(null));
                    statement.setString(5, String.valueOf(obj.getOrigem()));

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (!resultSet.next()) {
                            throw new SQLException(
                                    "Não foi possível obter o ID da cautela."
                            );
                        }

                        long idGerado = resultSet.getLong("id");
                        Date dataCriacao = resultSet.getDate("criado_em");

                        if (dataCriacao == null) {
                            throw new SQLException(
                                    "A data de criação da cautela não foi retornada."
                            );
                        }

                        LocalDate criadoEm = dataCriacao.toLocalDate();

                        obj.setId(Processo.IDFormatada(
                                idGerado,
                                criadoEm.getYear()
                        ));
                    }
                }

                patrimoniadoRepositorio.atualizarLocal(
                        connection,
                        obj.getItemPatrimoniado()
                );

                connection.commit();

            } catch (Exception e) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    e.addSuppressed(rollbackException);
                }

                throw new PersistenciaException(
                        "Não foi possível salvar a cautela.",
                        e
                );
            }

        } catch (SQLException e) {
            throw new PersistenciaException(
                    "Não foi possível salvar a cautela.",
                    e
            );
        }
    }

    @Override
    public List<Cautela> buscarUltimasInsercoes() throws SQLException {
        List<Cautela> cautelaList = new ArrayList<>();
        PatrimoniadoRepositorio patrimoniadoRepositorio = AppContext.getPatrimoniadoRepositorio();
        String sql = """
                SELECT * FROM cautelas
                ORDER BY criado_em DESC
                LIMIT 5
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

                ItemPatrimoniado itemPatrimoniado =
                        patrimoniadoRepositorio.buscarPorId(resultSet.getString("tombo_item"));

                Cautela novaCautela = new Cautela(
                        destinoLocal,
                        resultSet.getLong("id"),
                        resultSet.getString("observacoes"),
                        origemLocal,
                        itemPatrimoniado,
                        resultSet.getDate("criado_em").toLocalDate(),
                        resultSet.getString("emissor")
                );

                cautelaList.add(novaCautela);
            }

            return cautelaList;
        }
    }
}
