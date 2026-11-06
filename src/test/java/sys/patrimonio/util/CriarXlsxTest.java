package sys.patrimonio.util;

import org.junit.jupiter.api.Test;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.service.RelatoriosManager;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CriarXlsxTest {

    @Test
    void criarXlsx() {
        try {
            LocalDate dataInicial, dataFinal;
            dataInicial = LocalDate.of(2026, 10, 01);
            dataFinal = LocalDate.now();
            Status status = Status.NOVO;
            Localidade localidade = Localidade.TI;


            RelatoriosManager relatoriosManager = new RelatoriosManager(dataInicial, dataFinal, status, localidade);

            CriarXlsx criarXlsx = new CriarXlsx();
            criarXlsx.criarXlsx(relatoriosManager.gerarRelatorio());
        }catch (IOException e){
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}