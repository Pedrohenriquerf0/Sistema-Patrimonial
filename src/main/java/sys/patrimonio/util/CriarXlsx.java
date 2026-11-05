package sys.patrimonio.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sys.patrimonio.model.Item;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.ItemPatrimoniado;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class CriarXlsx {

    private static String criarCaminho() throws IOException {
        Path pasta = Paths.get(Processo.usuarioOS(), "Sistema de Patrimonio", "Relatorios");
        Files.createDirectories(pasta);
        return String.valueOf(pasta);
    }

    public void criarXlsx(List<Item> itemList) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = null;

        //  ESTILOS
        CellStyle estilo = workbook.createCellStyle();
        estilo.setAlignment(HorizontalAlignment.CENTER);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);

        // TODO LOGICA D ESEPARAÇÃO E CRIAÇÃO DE CADA SHEETS
        for (Item item : itemList) {

            if (item instanceof ItemPatrimoniado itemPatrimoniado) {

                sheet = workbook.getSheet("Patrimoniado");

                if (sheet == null) {
                    sheet = workbook.createSheet("Patrimoniado");

                    Row header = sheet.createRow(0);
                    header.createCell(0).setCellValue("NOME");
                    header.createCell(1).setCellValue("TOMBO");
                    header.createCell(2).setCellValue("NUMERO DE SERIE");
                    header.createCell(3).setCellValue("DESCRIÇÃO");
                    header.createCell(4).setCellValue("CATEGORIA");
                    header.createCell(5).setCellValue("STATUS");
                    header.createCell(6).setCellValue("LOCALIDADE");
                    header.createCell(7).setCellValue("QUANTIDADE");
                    //header.createCell(8).setCellValue("FOTO");
                }

                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue(itemPatrimoniado.getNome());
                header.createCell(1).setCellValue(itemPatrimoniado.getTombo());
                header.createCell(2).setCellValue(itemPatrimoniado.getNumeroSerie());
                header.createCell(3).setCellValue(itemPatrimoniado.getDescricao());
                header.createCell(4).setCellValue(itemPatrimoniado.getCategoria());
                header.createCell(5).setCellValue(String.valueOf(itemPatrimoniado.getStatus()));
                header.createCell(6).setCellValue(String.valueOf(itemPatrimoniado.getLocal()));
                header.createCell(7).setCellValue(itemPatrimoniado.getQuantidade());
                //header.createCell(8).setCellValue();
            }

            if (item instanceof ItemConsumo itemConsumo) {

                sheet = workbook.getSheet("Consumo");

                if (sheet == null) {
                    sheet = workbook.createSheet("Consumo");

                    Row header = sheet.createRow(0);
                    header.createCell(0).setCellValue("NOME");
                    header.createCell(1).setCellValue("DESCRIÇÃO");
                    header.createCell(2).setCellValue("CATEGORIA");
                    header.createCell(3).setCellValue("STATUS");
                    header.createCell(4).setCellValue("LOCALIDADE");
                    header.createCell(5).setCellValue("QUANTIDADE");
                    //header.createCell(6).setCellValue("FOTO");
                }

                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue(itemConsumo.getNome());
                header.createCell(1).setCellValue(itemConsumo.getDescricao());
                header.createCell(2).setCellValue(itemConsumo.getCategoria());
                header.createCell(3).setCellValue(String.valueOf(itemConsumo.getStatus()));
                header.createCell(4).setCellValue(String.valueOf(itemConsumo.getLocal()));
                header.createCell(5).setCellValue(itemConsumo.getQuantidade());
                //header.createCell(6).setCellValue();
            }
        }

        Path caminho = Paths.get(criarCaminho(), Processo.relatoriosNome());
        try (FileOutputStream output = new FileOutputStream(caminho.toFile())) {
            workbook.write(output);
        }
        workbook.close();
    }
}
