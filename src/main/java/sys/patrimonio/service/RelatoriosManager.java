package sys.patrimonio.service;

import sys.patrimonio.config.AppContext;
import sys.patrimonio.model.Item;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.repository.ConsumoRepositorio;
import sys.patrimonio.repository.PatrimoniadoRepositorio;
import sys.patrimonio.util.Processo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class RelatoriosManager {
    private final PatrimoniadoRepositorio patrimoniadoRepositorio;
    private final ConsumoRepositorio consumoRepositorio;
    private LocalDate dataInicial, datafinal;
    private Status status;
    private Localidade localidade;

    public RelatoriosManager(LocalDate dataInicial, LocalDate datafinal, Status status, Localidade localidade) {
        this.patrimoniadoRepositorio = AppContext.getPatrimoniadoRepositorio();
        this.consumoRepositorio = AppContext.getConsumoRepositorio();
        this.dataInicial = dataInicial;
        this.datafinal = datafinal;
        this.status = status;
        this.localidade = localidade;
    }

    public RelatoriosManager() {
        this.patrimoniadoRepositorio = AppContext.getPatrimoniadoRepositorio();
        this.consumoRepositorio = AppContext.getConsumoRepositorio();
    }

    public List<Item> gerarRelatorio() throws SQLException, IOException {
        return Stream.concat(patrimoniadoRepositorio.relatorio(this.dataInicial, this.datafinal, this.status, this.localidade).stream(), consumoRepositorio.relatorio(dataInicial, datafinal, status, localidade).stream())
                .map(item -> (Item) item).toList();
    }

    public Map<String, Integer> totais() throws SQLException {
        Map<String, Integer> totalpatrimonio = new HashMap<>();
        totalpatrimonio.put("patrimoniado", patrimoniadoRepositorio.listarTotal());
        totalpatrimonio.put("consumo", consumoRepositorio.listarTotal());
        return totalpatrimonio;
    }

}
