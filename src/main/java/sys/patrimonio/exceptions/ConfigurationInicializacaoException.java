package sys.patrimonio.exceptions;

public class ConfigurationInicializacaoException extends RuntimeException {

    public ConfigurationInicializacaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}