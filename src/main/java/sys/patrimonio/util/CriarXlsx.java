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

    public String criarXlsx(List<Item> itemList) throws IOException {

        if (!itemList.isEmpty()) {

            int linha = 0;
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = null;

            //  ESTILOS
            CellStyle estilo = workbook.createCellStyle();
            estilo.setAlignment(HorizontalAlignment.CENTER);
            estilo.setVerticalAlignment(VerticalAlignment.CENTER);


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
                        header.createCell(8).setCellValue("FOTO");
                    }

                    Row body = sheet.createRow(++linha);
                    body.createCell(0).setCellValue(itemPatrimoniado.getNome());
                    body.createCell(1).setCellValue(itemPatrimoniado.getTombo());
                    body.createCell(2).setCellValue(itemPatrimoniado.getNumeroSerie());
                    body.createCell(3).setCellValue(itemPatrimoniado.getDescricao());
                    body.createCell(4).setCellValue(itemPatrimoniado.getCategoria());
                    body.createCell(5).setCellValue(String.valueOf(itemPatrimoniado.getStatus()));
                    body.createCell(6).setCellValue(String.valueOf(itemPatrimoniado.getLocal()));
                    body.createCell(7).setCellValue(itemPatrimoniado.getQuantidade());

                    // Carregar imagem
                    byte[] bytes = ConverterArquivos.pathsToBytes(itemPatrimoniado.getFoto());

                    int pictureIndex = workbook.addPicture(
                            bytes,
                            Workbook.PICTURE_TYPE_PNG
                    );

                    CreationHelper helper = workbook.getCreationHelper();
                    Drawing<?> drawing = sheet.createDrawingPatriarch();

                    body.setHeightInPoints(80);
                    sheet.setColumnWidth(8, 20 * 256);

                    ClientAnchor anchor = helper.createClientAnchor();

                    anchor.setCol1(8); // Coluna I
                    anchor.setCol2(9); // Limite da coluna

                    anchor.setRow1(linha);
                    anchor.setRow2(linha + 1);
                    drawing.createPicture(anchor, pictureIndex);
                }

                if (item instanceof ItemConsumo itemConsumo) {

                    sheet = workbook.getSheet("Consumo");

                    if (sheet == null) {
                        sheet = workbook.createSheet("Consumo");
                        linha = 0;

                        Row header = sheet.createRow(0);
                        header.createCell(0).setCellValue("NOME");
                        header.createCell(1).setCellValue("DESCRIÇÃO");
                        header.createCell(2).setCellValue("CATEGORIA");
                        header.createCell(3).setCellValue("STATUS");
                        header.createCell(4).setCellValue("LOCALIDADE");
                        header.createCell(5).setCellValue("QUANTIDADE");
                        //header.createCell(6).setCellValue("FOTO");
                    }

                    Row body = sheet.createRow(++linha);
                    body.createCell(0).setCellValue(itemConsumo.getNome());
                    body.createCell(1).setCellValue(itemConsumo.getDescricao());
                    body.createCell(2).setCellValue(itemConsumo.getCategoria());
                    body.createCell(3).setCellValue(String.valueOf(itemConsumo.getStatus()));
                    body.createCell(4).setCellValue(String.valueOf(itemConsumo.getLocal()));
                    body.createCell(5).setCellValue(itemConsumo.getQuantidade());

                    // Carregar imagem
                    byte[] bytes = ConverterArquivos.pathsToBytes(itemConsumo.getFoto());

                    int pictureIndex = workbook.addPicture(
                            bytes,
                            Workbook.PICTURE_TYPE_PNG
                    );

                    CreationHelper helper = workbook.getCreationHelper();
                    Drawing<?> drawing = sheet.createDrawingPatriarch();

                    body.setHeightInPoints(80);
                    sheet.setColumnWidth(8, 20 * 256);

                    ClientAnchor anchor = helper.createClientAnchor();

                    anchor.setCol1(8); // Coluna I
                    anchor.setCol2(9); // Limite da coluna

                    anchor.setRow1(linha);
                    anchor.setRow2(linha + 1);
                    drawing.createPicture(anchor, pictureIndex);
                }
            }

            Path caminho = Paths.get(criarCaminho(), Processo.relatoriosNome());
            try (FileOutputStream output = new FileOutputStream(caminho.toFile())) {
                workbook.write(output);
            }
            workbook.close();

            return caminho.toString();
        }

        return null;
    }
}
