package sys.patrimonio.service;


import sys.patrimonio.config.AppContext;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.repository.PatrimoniadoRepositorio;

public class PatrimoniadoManager {
    private final PatrimoniadoRepositorio patrimoniadoRepositorio;

    public PatrimoniadoManager() {
        this.patrimoniadoRepositorio = AppContext.getPatrimoniadoRepositorio();
    }

    public void atualizar(ItemPatrimoniado patrimoniado) {
        if (patrimoniado == null) {
            throw new IllegalArgumentException("Item patrimoniado não pode ser nulo");
        }
        patrimoniadoRepositorio.atualizar(patrimoniado);
    }
}
