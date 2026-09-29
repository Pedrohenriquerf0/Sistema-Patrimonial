package sys.patrimonio.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Convert {
    public static byte[] pathsToBytes(String caminoFoto) throws IOException {
        return Files.readAllBytes(Path.of(caminoFoto));
    }

    public static String bytesToPaths(byte[] fotoBytes, String tombo) throws IOException{
        Path pasta = Paths.get(Processo.usuarioOS(), "Sistema de Patrimonio", "Fotos Temp");
        Files.createDirectories(pasta);
        Path arquivoFinal = pasta.resolve(tombo + ".png");
        Files.write(arquivoFinal, fotoBytes);
        return String.valueOf(arquivoFinal);
    }


}
