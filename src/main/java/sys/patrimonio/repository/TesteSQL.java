package sys.patrimonio.repository;

import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;

public class TesteSQL {
    public static void main(String[] args) {
        ItemPatrimoniado pc = new ItemPatrimoniado("PC", "AADC", "ASQW321546", "PC GAMER", Localidade.RH, "INFORMATICA", Status.BOM, "C:\\Users\\pedro.fernandes\\Documents\\teste_sql.png");

        PatrimoniadoRepositorio pr = new PatrimoniadoDAO(new ConnectionFactory());
        pr.salvar(pc);
    }
}
