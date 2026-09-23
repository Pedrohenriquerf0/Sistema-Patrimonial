package sys.patrimonio.service;

import sys.patrimonio.model.Cautela;
import sys.patrimonio.repository.CautelaRepositorio;
import sys.patrimonio.util.Processo;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CriarCautela {
    private Cautela cautela;
    private final CautelaRepositorio cautelaRepositorio;


    public CriarCautela(Cautela cautela, CautelaRepositorio cautelaRepositorio) {
        this.cautelaRepositorio = cautelaRepositorio;
        this.cautelaRepositorio.salvar(cautela);
        this.cautela.setId(Processo.IDFormatada(this.cautelaRepositorio.idCautela(cautela)));
        this.cautela = cautela;
    }


    public void visualizarCautela() {

        try {
            InputStream caminhoTemplate = getClass().getClassLoader().getResourceAsStream("cautelaTemplate.html");

            if (caminhoTemplate == null) {
                throw new RuntimeException("Template não encontrado dentro do JAR");
            }
            String htmlCautela = new String(caminhoTemplate.readAllBytes(), StandardCharsets.UTF_8);
            htmlCautela = htmlCautela.replace("{{idcautela}}", this.cautela.getId());
            htmlCautela = htmlCautela.replace("{{departamentoorigem}}", String.valueOf(cautela.getItemPatrimoniado().getLocal()));
            htmlCautela = htmlCautela.replace("{{departamentodestino}}", String.valueOf(cautela.getDestino()));
            htmlCautela = htmlCautela.replace("{{data}}", cautela.getData());
            htmlCautela = htmlCautela.replace("{{emissorcautela}}", cautela.getEmissor());
            htmlCautela = htmlCautela.replace("{{obs}}", cautela.getObservacoes().orElse("-"));
            htmlCautela = htmlCautela.replace("{{quantidade}}", String.valueOf(cautela.getItemPatrimoniado().getQuantidade()));
            htmlCautela = htmlCautela.replace("{{descricao}}", Processo.descricaoFormatada(cautela));
            htmlCautela = htmlCautela.replace("{{nome}}", cautela.getItemPatrimoniado().getNome());
            htmlCautela = htmlCautela.replace("{{tombo}}", cautela.getItemPatrimoniado().getTombo());
            htmlCautela = htmlCautela.replace("{{status}}", String.valueOf(cautela.getItemPatrimoniado().getStatus()));
            htmlCautela = htmlCautela.replace("{{foto}}", cautela.getItemPatrimoniado().getFoto());
            abriCautela(htmlCautela);
        } catch (IOException e) {
            throw new RuntimeException(e);  // TODO - criar exceptions para esse tipo
        }

    }

    private void abriCautela(String htmlCautela) {
        String novoCautela = "Cautela_ID_" + this.cautela.getId();
        novoCautela = novoCautela.replace("/", "-");
        try {
            Path pasta = Paths.get(Processo.usuarioOS(), "Sistema de Patrimonio", "Cautelas");
            Files.createDirectories(pasta);
            Path arquivoFinal = pasta.resolve(novoCautela + ".html");
            Files.writeString(arquivoFinal, htmlCautela);
            Desktop.getDesktop().browse(arquivoFinal.toUri());
        } catch (IOException e) {
            throw new RuntimeException(e);  // TODO - criar exceptions para esse tipo
        }

    }
}
