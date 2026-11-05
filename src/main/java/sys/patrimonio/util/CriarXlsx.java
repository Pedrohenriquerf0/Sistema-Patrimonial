package sys.patrimonio.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class CriarXlsx {

    private static String criarCaminho() throws IOException {
        Path pasta = Paths.get(Processo.usuarioOS(), "Sistema de Patrimonio", "Relatorios");
        Files.createDirectories(pasta);
        return String.valueOf(pasta);
    }

    public void criarXlsx() throws IOException {

        Workbook workbook = new XSSFWorkbook();

        //  ESTILOS
        CellStyle estilo = workbook.createCellStyle();
        estilo.setAlignment(HorizontalAlignment.CENTER);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);

        // TODO LOGICA D ESEPARAÇÃO E CRIAÇÃO DE CADA SHEETS
        Sheet movimentacoes = workbook.createSheet("Movimentações");

        Path caminho = Paths.get(criarCaminho(), Processo.relatoriosNome());
        try (FileOutputStream output = new FileOutputStream(caminho.toFile())) {
            workbook.write(output);
        }
        workbook.close();
    }
}
