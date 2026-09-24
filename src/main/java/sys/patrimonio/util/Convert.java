package sys.patrimonio.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Convert {
    public static byte[] pathsToBytes(String caminoFoto) throws IOException {
        return Files.readAllBytes(Path.of(caminoFoto));
    }
}
