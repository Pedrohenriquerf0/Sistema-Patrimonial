package sys.patrimonio.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.util.SeletorFoto;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerItemPatrimoniado implements Initializable {
    private ItemPatrimoniado patrimoniado;
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoTombo;
    @FXML
    private TextField campoNumeroSerie;
    @FXML
    private TextField campoCategoria;
    @FXML
    private TextField campoLocalidade;
    @FXML
    private TextField campoStatus;
    @FXML
    private TextField campoDescricao;
    @FXML
    private TextField campoEntrada;
    @FXML
    private TextField campoQuantidade;

    @FXML
    private ImageView imagemPatrimonio;

    @FXML
    private Label labelSemFoto;


    public void tranferiDados(ItemPatrimoniado patrimoniado) {
        this.patrimoniado = patrimoniado;
        carregarDados();
    }

    private void carregarDados() {
        campoNome.setText(patrimoniado.getNome());
        campoTombo.setText(patrimoniado.getTombo());
        campoNumeroSerie.setText(patrimoniado.getNumeroSerie());
        campoCategoria.setText(patrimoniado.getCategoria());
        campoLocalidade.setText(String.valueOf(patrimoniado.getLocal()));
        campoStatus.setText(String.valueOf(patrimoniado.getStatus()));
        campoDescricao.setText(patrimoniado.getDescricao());
        campoQuantidade.setText(Integer.toString(patrimoniado.getQuantidade()));
        campoEntrada.setText(String.valueOf(patrimoniado.getEntrada()));
        imagemPatrimonio.setImage(SeletorFoto.carregarReduzida(new File(patrimoniado.getFoto()), 272));
        labelSemFoto.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void btt_editar(){

    }

    @FXML
    private void btt_cautela(){

    }
}
