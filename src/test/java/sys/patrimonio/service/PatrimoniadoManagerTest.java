package sys.patrimonio.service;


import org.junit.jupiter.api.Test;
import sys.patrimonio.config.AppContext;
import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;
import sys.patrimonio.repository.*;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PatrimoniadoManagerTest {

    @Test
    void iniciarConfiguracaoDeContexto(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        PatrimoniadoRepositorio patrimoniadoRepositorio = new PatrimoniadoDAO(connectionFactory);
        ConsumoRepositorio consumoRepositorio = new ConsumoDAO(connectionFactory);
        CautelaRepositorio cautelaRepositorio = new CautelaDAO(connectionFactory);
        MovimentacaoRepositorio movimentacaoRepositorio = new MovimentacaoDAO(connectionFactory);

        AppContext.inicializar(cautelaRepositorio, movimentacaoRepositorio, consumoRepositorio, patrimoniadoRepositorio);

    }

    @Test
    void deveSalvarEBuscarItemPatrimoniadoPorNumeroDeSerie() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        PatrimoniadoRepositorio patrimoniadoRepositorio = new PatrimoniadoDAO(connectionFactory);
        ConsumoRepositorio consumoRepositorio = new ConsumoDAO(connectionFactory);
        CautelaRepositorio cautelaRepositorio = new CautelaDAO(connectionFactory);
        MovimentacaoRepositorio movimentacaoRepositorio = new MovimentacaoDAO(connectionFactory);

        AppContext.inicializar(cautelaRepositorio, movimentacaoRepositorio, consumoRepositorio, patrimoniadoRepositorio);
        // Arrange: Preparação dos dados (exatamente como fez no TesteSQL.java)
        String testeId = UUID.randomUUID().toString();
        String testeNumeroSerie = testeId + "NS";
        ItemPatrimoniado notebook = new ItemPatrimoniado(
                "LENOVO IDEAPAD 4", testeId, testeNumeroSerie,
                "NOTEBOOK GAMER", Localidade.TI, "INFORMATICA", Status.NOVO, "src/test/java/sys/patrimonio/service/foto_test.png"
        );
        PatrimoniadoManager manager = new PatrimoniadoManager();

        // Act: Execução das ações a testar
        manager.salvar(notebook);

        PatrimonioPesquisaService patrimonioPesquisaService = new PatrimonioPesquisaService(notebook.getNumeroSerie());
        ItemPatrimoniado resultado = patrimonioPesquisaService.pesquisa().stream()
                .filter(item -> item.getNome().equals("LENOVO IDEAPAD 4"))
                .map(item -> (ItemPatrimoniado) item) // Cast caso pesquisa() retorne uma List genérica de 'Item'
                .findFirst()
                .orElse(null); // Retorna null se não achar nada (o assertNotNull vai capturar isso logo abaixo)


        // Assert: Validação automática (substitui os System.out.println)
        assertNotNull(resultado, "O item deveria ter sido guardado e encontrado na base de dados.");
        assertEquals("LENOVO IDEAPAD 4", resultado.getNome(), "O nome do item procurado deve coincidir.");
        assertEquals(Localidade.TI, resultado.getLocal(), "A localidade deve ser TI.");
    }
}