package sys.patrimonio.service;

import sys.patrimonio.model.Cautela;
import sys.patrimonio.repository.RepositorioCautela;
import sys.patrimonio.util.CautelaProcesso;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CriarCautela {
    private String id;
    private Cautela cautela;


    public CriarCautela(Cautela cautela) {
        RepositorioCautela.adicionarCautela(cautela);
        this.id = CautelaProcesso.IDFormatada(RepositorioCautela.idCautela(cautela));
        this.cautela = cautela;
        System.out.println("Cautela criada id " + this.id);
    }


    public void visualizarCautela() {

        try {
            InputStream caminhoTemplate = getClass().getClassLoader().getResourceAsStream("cautelaTemplate.html");

            if (caminhoTemplate == null) {
                throw new RuntimeException("Templatenão encontrado dentro do JAR");
            }
            String htmlCautela = new String(caminhoTemplate.readAllBytes(), StandardCharsets.UTF_8);
            htmlCautela = htmlCautela.replace("{{idcautela}}", this.id);
            htmlCautela = htmlCautela.replace("{{departamentoorigem}}", String.valueOf(cautela.getItemPatrimonio().getLocal()));
            htmlCautela = htmlCautela.replace("{{departamentodestino}}", String.valueOf(cautela.getDestino()));
            htmlCautela = htmlCautela.replace("{{data}}", cautela.getData());
            htmlCautela = htmlCautela.replace("{{emissorcautela}}", cautela.getEmissor());
            htmlCautela = htmlCautela.replace("{{obs}}", cautela.getObsevacoes());
            htmlCautela = htmlCautela.replace("{{quandidade}}", String.valueOf(cautela.getItemPatrimonio().getQuantidade()));
            htmlCautela = htmlCautela.replace("{{descricao}}", CautelaProcesso.descricaoFormatada(cautela));
            htmlCautela = htmlCautela.replace("{{nome}}", cautela.getItemPatrimonio().getNome());
            htmlCautela = htmlCautela.replace("{{tombo}}", cautela.getItemPatrimonio().getTombo());
            htmlCautela = htmlCautela.replace("{{status}}", String.valueOf(cautela.getItemPatrimonio().getStatus()));
            htmlCautela = htmlCautela.replace("{{foto}}", cautela.getItemPatrimonio().getFoto());
            abriCautela(htmlCautela);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void abriCautela(String htmlCautela) {
        String novoCautela = "Cautela_ID_" + this.id;
        novoCautela = novoCautela.replace("/", "-");
        try {
            Path pasta = Paths.get(CautelaProcesso.usuarioOS(), "Sistema de Patrimonio", "Cautelas");
            Files.createDirectories(pasta);
            Path arquivoFinal = pasta.resolve(novoCautela + ".html");
            Files.writeString(arquivoFinal, htmlCautela);
            Desktop.getDesktop().browse(arquivoFinal.toUri());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
