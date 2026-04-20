package sys.patrimonio.model;

import sys.patrimonio.util.DataFormatada;
import sys.patrimonio.util.EmissorCautela; //criar uma instancia, guarda no banco e gera o html para imprimir

public class Cautela {
    private Localidade destino;
    private String emissor;
    private String data;
    private ItemPatrimonio itemPatrimonio;
    private int idCautela;
    private String obsevacoa;


    public Cautela(ItemPatrimonio itemPatrimonio) {
        this.itemPatrimonio = itemPatrimonio;
        this.data = DataFormatada.dataAgora();
        this.emissor = EmissorCautela.emissorCautela();

    }


    @Override
    public String toString() {
        return "Cautela{" +
                "emissor='" + emissor + '\'' +
                ", data='" + data + '\'' +
                ", itemPatrimonio=" + itemPatrimonio +
                ", idCautela='" + idCautela + '\'' +
                '}';
    }
}
