package sys.patrimonio.service;

import sys.patrimonio.config.AppContext;
import sys.patrimonio.model.Item;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.repository.ConsumoRepositorio;
import sys.patrimonio.repository.PatrimoniadoRepositorio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class PatrimonioPesquisaService {
    private final PatrimoniadoRepositorio patrimoniadoRepositorio;
    private final ConsumoRepositorio consumoRepositorio;
    private String pesquisa;

    public PatrimonioPesquisaService(String pesquisa) {
        this.pesquisa = pesquisa.trim();
        this.consumoRepositorio = AppContext.getConsumoRepositorio();
        this.patrimoniadoRepositorio = AppContext.getPatrimoniadoRepositorio();
    }

    public List<Item> pesquisa() {

        if (pesquisa.matches("\\d+")) {
            return pesquisaConsumoID().stream().map(item -> (Item) item).toList();
        } else if (pesquisa.chars().anyMatch(Character::isDigit)) {
            if (pesquisa.length() <= 10) {
                return pesquisaPatrimoniadoTombo().stream().map(item -> (Item) item).toList();
            } else if (pesquisa.length() <= 15) {
                return pesquisaPatrimoniadoNS().stream().map(item -> (Item) item).toList();
            }
        }

        return Stream.concat(pesquisaConsumoNome().stream(), pesquisaPatrimoniadoNome().stream())
                .map(item -> (Item) item).toList();
    }

    private List<ItemConsumo> pesquisaConsumoNome() {
        return consumoRepositorio.buscarPorNome(pesquisa);
    }

    private List<ItemConsumo> pesquisaConsumoID() {
        Long id = Long.parseLong(pesquisa);
        Optional<ItemConsumo> consumoResultado = Optional.ofNullable(consumoRepositorio.buscarPorId(id));
        return consumoResultado.map(List::of).orElseGet(List::of);
    }

    private List<ItemPatrimoniado> pesquisaPatrimoniadoNome() {
        return patrimoniadoRepositorio.buscarPorNome(pesquisa);
    }

    private List<ItemPatrimoniado> pesquisaPatrimoniadoTombo() {
        Optional<ItemPatrimoniado> patrimoniadoResultado = Optional.ofNullable(patrimoniadoRepositorio.buscarPorId(pesquisa));
        return patrimoniadoResultado.map(List::of).orElseGet(List::of);
    }

    private List<ItemPatrimoniado> pesquisaPatrimoniadoNS() {
        Optional<ItemPatrimoniado> patrimoniadoResultado = Optional.ofNullable(patrimoniadoRepositorio.buscarPorNS(pesquisa));
        return patrimoniadoResultado.map(List::of).orElseGet(List::of);
    }


}
