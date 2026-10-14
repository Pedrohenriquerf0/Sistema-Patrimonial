package sys.patrimonio.exceptions;

public class ConsultaException  extends PersistenciaException {
    public ConsultaException (String message, Throwable causa) {
        super(message, causa);
    }
}
