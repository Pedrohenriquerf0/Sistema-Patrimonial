package sys.patrimonio.repository;

import sys.patrimonio.model.Departamento;
import sys.patrimonio.model.ItemPatrimonio;
import sys.patrimonio.model.Status;

public interface MetodosPatrimonio {
    public void adiconarItems(ItemPatrimonio item);
    public void registroSaida(ItemPatrimonio item);
    public void registroSaida(ItemPatrimonio item, int quantidade);
    public void itemDanificado(ItemPatrimonio item);
    public void mudarStatus(ItemPatrimonio item, Status status); // itemDanificado seria a mesma coisa, ajustar no futuro
    public void transferencia(ItemPatrimonio item, Departamento departamento);
    public void listagem();
    public void relatorio();
}
