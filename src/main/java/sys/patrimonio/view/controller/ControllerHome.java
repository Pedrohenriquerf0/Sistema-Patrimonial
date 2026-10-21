package sys.patrimonio.view.controller;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sys.patrimonio.model.Item;
import sys.patrimonio.model.ItemConsumo;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.service.PatrimonioPesquisaService;
import sys.patrimonio.util.LoaderTelas;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHome implements Initializable {

    @FXML
    private ControllerSidebar sidebarController;

    @FXML
    private TextField text_pesquisa;

    @FXML
    private TableView<Item> tabela_view;

    @FXML
    private TableColumn<Item, String> colunaFoto;

    @FXML
    private TableColumn<Item, String> colunaNome;

    @FXML
    private TableColumn<Item, String> colunaID;

    @FXML
    private TableColumn<Item, String> colunaCategoria;

    @FXML
    private TableColumn<Item, String> colunastatus;

    @FXML
    private TableColumn<Item, String> colunaLocalidade;

    @FXML
    private TableColumn<Item, Number> colunaQuant;

    @FXML
    private void pesquisaPatrimonio() {
        tabela_view.getItems().clear();
        String pesquisa = text_pesquisa.getText();

        if (pesquisa == null || pesquisa.isBlank()) {
            tabela_view.getItems().clear();
            return;
        }

        PatrimonioPesquisaService service =
                new PatrimonioPesquisaService(pesquisa.trim());

        tabela_view.getItems().setAll(service.pesquisa());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (sidebarController != null) {
            sidebarController.ativarHome();
        }

        configurarColunas();

        tabela_view.setRowFactory(tv -> {
            TableRow<Item> linha = new TableRow<>();

            linha.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2
                        && !linha.isEmpty()) {

                    Item itemSelecionado = linha.getItem();
                    abrirDetalhes(itemSelecionado, event);
                }
            });

            return linha;
        });
    }

    private void abrirDetalhes(Item item, MouseEvent event) {
        if (item instanceof ItemPatrimoniado patrimonio) {
            ControllerItemPatrimoniado controllerItemPatrimoniado = new ControllerItemPatrimoniado();
            LoaderTelas.loaderTela(item, event, controllerItemPatrimoniado, "view/ItemPatrimoniado.fxml");
        } else if (item instanceof ItemConsumo consumo) {
            ControllerItemConsumo controllerItemConsumo = new ControllerItemConsumo();
            LoaderTelas.loaderTela(item, event, controllerItemConsumo, "view/ItemConsumo.fxml");
        }
    }

    private void configurarColunas() {

        // Foto
        colunaFoto.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(
                        cell.getValue().getFoto()
                )
        );

        colunaFoto.setCellFactory(coluna -> new TableCell<>() {
            private final ImageView imageView = new ImageView();

            {
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                imageView.setPreserveRatio(true);
            }

            @Override
            protected void updateItem(String caminho, boolean empty) {
                super.updateItem(caminho, empty);

                imageView.setImage(null);
                setGraphic(null);

                if (empty || caminho == null || caminho.isBlank()) {
                    return;
                }

                File arquivo = new File(caminho);

                if (!arquivo.isFile()) {
                    return;
                }

                Image imagem = new Image(
                        arquivo.toURI().toString(),
                        50,
                        50,
                        true,
                        true
                );

                if (!imagem.isError()) {
                    imageView.setImage(imagem);
                    setGraphic(imageView);
                }
            }
        });

        // Nome
        colunaNome.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(
                        cell.getValue().getNome()
                )
        );

        // Tombo
        colunaID.setCellValueFactory(cell -> {
            Item item = cell.getValue();

            if (item instanceof ItemPatrimoniado patrimonio) {
                return new ReadOnlyStringWrapper(patrimonio.getTombo());
            }

            if (item instanceof ItemConsumo consumo) {
                return new ReadOnlyStringWrapper(
                        String.valueOf(consumo.getId())
                );
            }

            return new ReadOnlyStringWrapper("");
        });

        // Categoria
        colunaCategoria.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(
                        cell.getValue().getCategoria()
                )
        );

        // Status
        colunastatus.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(
                        String.valueOf(cell.getValue().getStatus())
                )
        );

        // Localidade
        colunaLocalidade.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(
                        String.valueOf(cell.getValue().getLocal())
                )
        );

        // Quantidade
        colunaQuant.setCellValueFactory(cell ->
                new ReadOnlyIntegerWrapper(
                        cell.getValue().getQuantidade()
                )
        );
    }
}