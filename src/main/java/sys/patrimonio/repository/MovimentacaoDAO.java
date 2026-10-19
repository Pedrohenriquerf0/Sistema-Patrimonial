package sys.patrimonio.repository;

import sys.patrimonio.config.AppContext;
import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.exceptions.ConsultaException;
import sys.patrimonio.exceptions.PersistenciaException;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.MovimentacaoItemConsumo;
import sys.patrimonio.model.TipoMovimentacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovimentacaoDAO implements MovimentacaoRepositorio {
    private final ConnectionFactory connectionFactory;

    public MovimentacaoDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public MovimentacaoItemConsumo buscarPorId(Long id) {
        ConsumoRepositorio consumoRepositorio = AppContext.getConsumoRepositorio();
        String sql = """
                SELECT * FROM movimentacao_consumo
                WHERE id = ?
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ItemConsumo itemConsumo =
                            consumoRepositorio.buscarPorId(resultSet.getLong("item_consumo_id"));

                    LocalDate data = resultSet.getDate("criado_em").toLocalDate();

                    Localidade destino = Optional.ofNullable(resultSet.getString("destino"))
                            .filter(s -> !s.isBlank())
                            .map(Localidade::valueOf)
                            .orElse(null);

                    TipoMovimentacao tipoMovimentacao =
                            Optional.ofNullable(resultSet.getString("tipo_movimentacao"))
                                    .filter(s -> !s.isBlank())
                                    .map(TipoMovimentacao::valueOf)
                                    .orElse(null);

                    return new MovimentacaoItemConsumo(
                            destino,
                            resultSet.getString("emissor"),
                            data,
                            itemConsumo,
                            tipoMovimentacao,
                            resultSet.getInt("quantidade"),
                            resultSet.getLong("id")
                    );
                }
            }
        } catch (SQLException e) {
            throw new ConsultaException(
                    "Não foi possível buscar a movimentação pelo ID informado.",
                    e
            );
        }

        return null;
    }

    @Override
    public List<MovimentacaoItemConsumo> listarTodos() {
        List<MovimentacaoItemConsumo> movimentacoes = new ArrayList<>();
        ConsumoRepositorio consumoRepositorio = AppContext.getConsumoRepositorio();
        String sql = """
                SELECT * FROM movimentacao_consumo
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ItemConsumo itemConsumo =
                        consumoRepositorio.buscarPorId(resultSet.getLong("item_consumo_id"));

                LocalDate data = resultSet.getDate("criado_em").toLocalDate();

                Localidade destino = Optional.ofNullable(resultSet.getString("destino"))
                        .filter(s -> !s.isBlank())
                        .map(Localidade::valueOf)
                        .orElse(null);

                TipoMovimentacao tipoMovimentacao =
                        Optional.ofNullable(resultSet.getString("tipo_movimentacao"))
                                .filter(s -> !s.isBlank())
                                .map(TipoMovimentacao::valueOf)
                                .orElse(null);

                MovimentacaoItemConsumo movimentacao = new MovimentacaoItemConsumo(
                        destino,
                        resultSet.getString("emissor"),
                        data,
                        itemConsumo,
                        tipoMovimentacao,
                        resultSet.getInt("quantidade"),
                        resultSet.getLong("id")
                );

                movimentacoes.add(movimentacao);
            }

            return movimentacoes;
        } catch (SQLException e) {
            throw new ConsultaException(
                    "Não foi possível listar as movimentações.",
                    e
            );
        }
    }

    @Override

    public void salvar(MovimentacaoItemConsumo obj) {
        ConsumoRepositorio consumoRepositorio =
                AppContext.getConsumoRepositorio();

        String sql = """
            INSERT INTO movimentacao_consumo
            (item_consumo_id, tipo_movimentacao, destino, quantidade, emissor)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection connection = this.connectionFactory.getConnection()) {
            connection.setAutoCommit(false);

            try {
                long idGerado;

                try (PreparedStatement statement = connection.prepareStatement(
                        sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

                    statement.setLong(1, obj.getItemConsumo().getId());
                    statement.setString(
                            2, String.valueOf(obj.getTipoMovimentacao()));
                    statement.setString(
                            3, String.valueOf(obj.getDestino()));
                    statement.setInt(4, obj.getQuantidade());
                    statement.setString(5, obj.getEmissor());

                    statement.executeUpdate();

                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (!resultSet.next()) {
                            throw new SQLException(
                                    "Não foi possível obter o ID da movimentação.");
                        }

                        idGerado = resultSet.getLong(1);
                    }
                }
                consumoRepositorio.atualizarQuantidade(
                        connection,
                        obj.getItemConsumo()
                );

                // Confirma as duas operações.
                connection.commit();

                obj.setId(idGerado);

            } catch (Exception e) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    e.addSuppressed(rollbackException);
                }

                throw e;
            }

        } catch (Exception e) {
            throw new PersistenciaException(
                    "Não foi possível salvar a movimentação.",
                    e
            );
        }
    }

}
