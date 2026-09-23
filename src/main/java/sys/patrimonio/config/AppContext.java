package sys.patrimonio.config;

import sys.patrimonio.repository.CautelaRepositorio;
import sys.patrimonio.repository.ConsumoRepositorio;
import sys.patrimonio.repository.MovimentacaoRepositorio;
import sys.patrimonio.repository.PatrimoniadoRepositorio;

public class AppContext {
    private static CautelaRepositorio cautelaRepositorio;
    private static MovimentacaoRepositorio movimentacaoRepositorio;
    private static ConsumoRepositorio consumoRepositorio;
    private static PatrimoniadoRepositorio patrimoniadoRepositorio;


    private AppContext() {

    }

    public static void inicializar(CautelaRepositorio cautelaRepo, MovimentacaoRepositorio movimentacaoRepo,ConsumoRepositorio consumoRepo, PatrimoniadoRepositorio patrimoniadoRepo) {
        cautelaRepositorio = cautelaRepo;
        movimentacaoRepositorio = movimentacaoRepo;
        consumoRepositorio = consumoRepo;
        patrimoniadoRepositorio = patrimoniadoRepo;
    }

    public static CautelaRepositorio getCautelaRepositorio() {
        return cautelaRepositorio;
    }

    public static PatrimoniadoRepositorio getPatrimoniadoRepositorio() {
        return patrimoniadoRepositorio;
    }

    public static MovimentacaoRepositorio getMovimentacaoRepositorio() {
        return movimentacaoRepositorio;
    }

    public static ConsumoRepositorio getConsumoRepositorio() {
        return consumoRepositorio;
    }
}
