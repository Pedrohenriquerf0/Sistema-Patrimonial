package sys.patrimonio.exceptions;

public class PatrimonioExistenteException extends RuntimeException {
    public PatrimonioExistenteException(String message, Throwable causa) {
        super(message, causa);
    }
}
