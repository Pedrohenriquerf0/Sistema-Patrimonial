package sys.patrimonio.repository;


import sys.patrimonio.config.AppContext;
import sys.patrimonio.config.ConnectionFactory;
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
    public MovimentacaoItemConsumo buscarPorId(Long aLong) {
        ConsumoRepositorio consumoRepositorio = AppContext.getConsumoRepositorio();
        String sql =  """
                SELECT * FROM movimentacao_consumo
                WHERE id = ?
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, aLong);
            ResultSet resultSet = statement.executeQuery();

           if(resultSet.next()) {
                ItemConsumo itemConsumo = consumoRepositorio.buscarPorId(resultSet.getLong("item_consumo_id"));

                LocalDate data = resultSet.getDate("criado_em").toLocalDate();

                Localidade destino = Optional.ofNullable(resultSet.getString("destino"))
                        .filter(s -> !s.isBlank())
                        .map(Localidade::valueOf)
                        .orElse(null);

                TipoMovimentacao tipoMovimentacao = Optional.ofNullable(resultSet.getString("tipo_movimentacao"))
                        .filter(s -> !s.isBlank()).map(TipoMovimentacao::valueOf).orElse(null);

                MovimentacaoItemConsumo novamovimentacao = new MovimentacaoItemConsumo(destino,
                        resultSet.getString("emissor"), data, itemConsumo, tipoMovimentacao, resultSet.getLong("id"));

                return novamovimentacao;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MovimentacaoItemConsumo> listarTodos() {
        List<MovimentacaoItemConsumo> consumoList = new ArrayList<>();
        ConsumoRepositorio consumoRepositorio = AppContext.getConsumoRepositorio();
        String sql = """
                SELECT * FROM movimentacao_consumo
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ItemConsumo itemConsumo = consumoRepositorio.buscarPorId(resultSet.getLong("item_consumo_id"));

                LocalDate data = resultSet.getDate("criado_em").toLocalDate();

                Localidade destino = Optional.ofNullable(resultSet.getString("destino"))
                        .filter(s -> !s.isBlank())
                        .map(Localidade::valueOf)
                        .orElse(null);

                TipoMovimentacao tipoMovimentacao = Optional.ofNullable(resultSet.getString("tipo_movimentacao"))
                        .filter(s -> !s.isBlank()).map(TipoMovimentacao::valueOf).orElse(null);

                MovimentacaoItemConsumo novamovimentacao = new MovimentacaoItemConsumo(destino,
                        resultSet.getString("emissor"), data, itemConsumo, tipoMovimentacao, resultSet.getLong("id"));

                consumoList.add(novamovimentacao);
            }
            return consumoList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consumoList;
    }

    @Override
    public void salvar(MovimentacaoItemConsumo obj) {
        String sql = """
                INSERT INTO movimentacao_consumo
                (item_consumo_id, tipo_movimentacao, destino, emissor)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = this.connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, obj.getItemConsumo().getId());
            statement.setString(2, String.valueOf(obj.getTipoMovimentacao()));
            statement.setString(3, String.valueOf(obj.getDestino()));
            statement.setString(4, obj.getEmissor());

            statement.executeUpdate();

            //TODO TALVEZ NAO VOU USA, MAS DEPOIS EU TIRO
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                long idGerado = resultSet.getLong(1);
                obj.setId(idGerado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
