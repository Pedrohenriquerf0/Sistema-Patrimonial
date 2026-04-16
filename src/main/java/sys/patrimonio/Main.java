package sys.patrimonio;

import sys.patrimonio.model.Cautela;
import sys.patrimonio.model.Departamento;
import sys.patrimonio.model.ItemsPatrimonio;
import sys.patrimonio.model.Status;
import sys.patrimonio.repository.RepositorioPatrimonio;



public class Main {


    public static void main(String[] args) {
        Departamento ti = new Departamento("TI");
        Departamento rh = new Departamento("RH");
        ItemsPatrimonio pc = new ItemsPatrimonio("PC", "AADC", "ASQW321546", "PC GAMER", ti, "INFORMATICA", "C:\\Users\\pedro.fernandes\\Documents\\teste.png");
        RepositorioPatrimonio repositorioPatrimonio = new RepositorioPatrimonio();

        repositorioPatrimonio.adiconarItems(pc);
        repositorioPatrimonio.listagem();

        System.out.println("====================================================");


        repositorioPatrimonio.itemDanificado(pc);
        repositorioPatrimonio.listagem();
        System.out.println("====================================================");

        repositorioPatrimonio.mudarStatus(pc, Status.OTIMO);
        repositorioPatrimonio.listagem();
        System.out.println("====================================================");
        repositorioPatrimonio.transferencia(pc, rh);
        repositorioPatrimonio.listagem();

        System.out.println("====================================================");

        repositorioPatrimonio.relatorio();

        Cautela cautela = new Cautela(pc);
        System.out.println(cautela.toString());
        Cautela cautela2 = new Cautela(pc);
        System.out.println(cautela2.toString());
    }
}
