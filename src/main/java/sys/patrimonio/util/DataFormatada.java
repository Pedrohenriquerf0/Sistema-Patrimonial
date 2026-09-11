package sys.patrimonio.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DataFormatada {
    private static DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy");


    public static String dataAgora(){
        return LocalDateTime.now().format(dateTimeFormatter);
    }
    public static int anoAgora(){
        return LocalDateTime.now().getYear();
    }
}
