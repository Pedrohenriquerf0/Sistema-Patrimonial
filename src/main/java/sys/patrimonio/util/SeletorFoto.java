package sys.patrimonio.util;

import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.stage.Window;

import java.io.File;

public final class SeletorFoto {
    private SeletorFoto() {}

    public static File escolher(Window dono) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Selecionar foto");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imagens (*.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg"));
        return chooser.showOpenDialog(dono);
    }

    public static Image carregarReduzida(File arquivo, int lado) {
        return new Image(arquivo.toURI().toString(), lado, lado, true, true);
    }
}
