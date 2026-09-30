package sys.patrimonio.service;


import sys.patrimonio.config.ConnectionFactory;
import sys.patrimonio.model.ItemPatrimoniado;
import sys.patrimonio.repository.PatrimoniadoDAO;
import sys.patrimonio.repository.PatrimoniadoRepositorio;

public class PatrimoniadoManager {

    public static void atualizar(ItemPatrimoniado patrimoniado){
        if(patrimoniado == null){
            // TODO RETURN ERRO
            System.out.println("ERRO");
        }else{
            PatrimoniadoRepositorio patrimoniadoRepositorio = new PatrimoniadoDAO(new ConnectionFactory());
            patrimoniadoRepositorio.atualizar(patrimoniado);
        }
    }

}
