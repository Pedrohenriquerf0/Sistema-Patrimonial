package sys.patrimonio.exceptions;

public class AppContextInicializacaoException extends RuntimeException {

    public AppContextInicializacaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}