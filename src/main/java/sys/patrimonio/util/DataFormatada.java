package sys.patrimonio.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DataFormatada {
    private static LocalDateTime localDate = LocalDateTime.now();
    private static DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy");


    public static String dataAgora(){
        return localDate.format(dateTimeFormatter);
    }
    public static int anoAgora(){
        return localDate.getYear();
    }
}
