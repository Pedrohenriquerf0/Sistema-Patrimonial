package sys.patrimonio.exceptions;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable causa) {
        super(message, causa);
    }
}
