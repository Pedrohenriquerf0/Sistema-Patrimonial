package sys.patrimonio.util;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DataFormatada {
    private static DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy");
        private static DateTimeFormatter dateFormatterTime =  DateTimeFormatter.ofPattern("dd'_'MM'_'yyyy HH_mm");


    public static String dataAgora(){
        return LocalDate.now().format(dateTimeFormatter);
    }
    public static String dataFormat(LocalDate data){
        return data.format(dateTimeFormatter);
    }

    public static String dataformatArquivo(){
        return LocalDateTime.now().format(dateFormatterTime);
    }

}
