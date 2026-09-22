package sys.patrimonio.repository;

import sys.patrimonio.model.MovimentacaoItemConsumo;

import java.util.List;

public interface MovimentacaoRepositorio extends RepositorioSomenteInsercao<MovimentacaoItemConsumo, Long>{
    @Override
    MovimentacaoItemConsumo buscarPorId(Long aLong);

    @Override
    List<MovimentacaoItemConsumo> listarTodos();

    @Override
    void salvar(MovimentacaoItemConsumo obj);
}
