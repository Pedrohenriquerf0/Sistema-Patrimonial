package sys.patrimonio.model;

public class UsuarioPatrimonio {

    public static String UsuarioPatrimonio() {
        return System.getProperty("user.name").toUpperCase().replace(".", " ");

    }
}
