package sys.patrimonio.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Datas {
    private static LocalDateTime localDate = LocalDateTime.now();
    private static DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy");


    public static String dataAgora(){
        return localDate.format(dateTimeFormatter);
    }
    public static int anoAgora(){
        return localDate.getYear();
    }
}
