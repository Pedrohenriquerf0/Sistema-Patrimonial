package sys.patrimonio.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Relatorios<T, Filtro> {
    public Optional<List<T>> relatorio(Predicate<Filtro> filtroPredicate) throws SQLException;
}
