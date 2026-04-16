package sys.patrimonio.model;

public class Cautela {
    private String usuarioPatrimonio;
    private String data;
    private ItemsPatrimonio itemPatrimonio;
    private StringBuilder numeroCautela;
    private String cautelaHTML = "";


    public Cautela(ItemsPatrimonio itemPatrimonio) {
        this.itemPatrimonio = itemPatrimonio;
        this.data = Datas.dataAgora();
        this.usuarioPatrimonio = UsuarioPatrimonio.UsuarioPatrimonio();
        setNumeroCautela();

    }

    private void setNumeroCautela(){ // vai gera o numero da cautela 0000/ano
        int id = 0001;
        int ano = Datas.anoAgora();
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
