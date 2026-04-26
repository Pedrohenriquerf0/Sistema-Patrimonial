package sys.patrimonio;

import sys.patrimonio.model.*;
import sys.patrimonio.service.CriarCautela;
import sys.patrimonio.service.ItemManager;


public class Main {


    public static void main(String[] args) {
        ItemPatrimonio pc = new ItemPatrimonio("PC", "AADC", "ASQW321546", "PC GAMER", Localidade.TI,"INFORMATICA", "C:\\Users\\pedro.fernandes\\Documents\\teste.png");
        ItemManager itemManager = new ItemManager();

        itemManager.adcionarItem(pc);
        itemManager.listagem();

        System.out.println("====================================================");



        itemManager.mudarStatus(pc, Status.DANIFICADO);
        itemManager.listagem();
        System.out.println("====================================================");

        itemManager.mudarStatus(pc, Status.OTIMO);
        itemManager.listagem();
        System.out.println("====================================================");
        itemManager.remanejaItem(pc, Localidade.ADMINISTRATIVO);
        itemManager.listagem();

        System.out.println("====================================================");

        Cautela cautela = new Cautela(pc, Localidade.COMECIAL);

        System.out.println("====================================================");

        Cautela cautela2 = new Cautela(pc, Localidade.ESTOQUE);
        CriarCautela cautela1 = new CriarCautela(cautela2);
        cautela1 = new CriarCautela(cautela);


        System.out.println("====================================================");

        cautela1.visualizarCautela();
    }
}
