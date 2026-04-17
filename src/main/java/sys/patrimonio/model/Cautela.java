package sys.patrimonio.model;

import sys.patrimonio.util.DataFormatada;

public class Cautela {
    private String usuarioPatrimonio;
    private String data;
    private ItemPatrimonio itemPatrimonio;
    private StringBuilder numeroCautela;
    private String cautelaHTML = "";


    public Cautela(ItemPatrimonio itemPatrimonio) {
        this.itemPatrimonio = itemPatrimonio;
        this.data = DataFormatada.dataAgora();
        this.usuarioPatrimonio = UsuarioPatrimonio.UsuarioPatrimonio();
        setNumeroCautela();

    }

    private void setNumeroCautela(){ // vai gera o numero da cautela 0000/ano
        int id = 0001;
        int ano = DataFormatada.anoAgora();
        this.numeroCautela = new StringBuilder(String.format("%04d/%d", id, ano));
        id++;
    }

    @Override
    public String toString() {
        return "Cautela{" +
                "usuarioPatrimonio='" + usuarioPatrimonio + '\'' +
                ", data='" + data + '\'' +
                ", itemPatrimonio=" + itemPatrimonio +
                ", numeroCautela='" + numeroCautela+ '\'' +
                ", cautelaHTML='" + cautelaHTML + '\'' +
                '}';
    }
}
