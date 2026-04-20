package sys.patrimonio;

import sys.patrimonio.model.*;
import sys.patrimonio.repository.RepositorioPatrimonio;



public class Main {


    public static void main(String[] args) {
        ItemPatrimonio pc = new ItemPatrimonio("PC", "AADC", "ASQW321546", "PC GAMER", Localidade.TI,"INFORMATICA", "C:\\Users\\pedro.fernandes\\Documents\\teste.png");
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
        repositorioPatrimonio.transferencia(pc, Localidade.ADMINISTRATIVO);
        repositorioPatrimonio.listagem();

        System.out.println("====================================================");

        repositorioPatrimonio.relatorio();

        Cautela cautela = new Cautela(pc);
        System.out.println(cautela.toString());
        Cautela cautela2 = new Cautela(pc);
        System.out.println(cautela2.toString());
    }
}
