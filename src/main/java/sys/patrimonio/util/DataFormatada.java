package sys.patrimonio.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DataFormatada {
    private static DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy");


    public static String dataAgora(){
        return LocalDate.now().format(dateTimeFormatter);
    }
    public static String dataFormat(LocalDate data){
        return data.format(dateTimeFormatter);
    }

}
