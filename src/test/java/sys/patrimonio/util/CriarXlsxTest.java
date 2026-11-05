package sys.patrimonio.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CriarXlsxTest {

    @Test
    void criarXlsx() {
        try {
            CriarXlsx criarXlsx = new CriarXlsx();
            criarXlsx.criarXlsx(null); // erro  de proposito
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}