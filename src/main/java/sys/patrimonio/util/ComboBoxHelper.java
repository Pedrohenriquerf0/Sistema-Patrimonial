package sys.patrimonio.util;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import sys.patrimonio.model.Localidade;
import sys.patrimonio.model.Status;

public final class ComboBoxHelper {

    public static void carregarLocalidade(ComboBox<Localidade> localidadeComboBox) {
        localidadeComboBox.getItems().setAll(Localidade.values());
    }

    public static void carregarStatus(ComboBox<Status> statusComboBox) {
        statusComboBox.getItems().setAll(Status.values());
    }


    public static void configurarCelulasComboBox(ComboBox<Localidade> localidadeComboBox, ComboBox<Status> statusComboBox) {
        statusComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Status item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(statusComboBox.getPromptText());
                }else{
                    setText(item.toString());
                }
            }
        });

        localidadeComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Localidade item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(localidadeComboBox.getPromptText());
                }else{
                    setText(item.toString());
                }
            }
        });
    }
}