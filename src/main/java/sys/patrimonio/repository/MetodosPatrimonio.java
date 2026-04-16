package sys.patrimonio.repository;

import sys.patrimonio.model.Departamento;
import sys.patrimonio.model.ItemsPatrimonio;
import sys.patrimonio.model.Status;

public interface MetodosPatrimonio {
    public void adiconarItems(ItemsPatrimonio item);
    public void registroSaida(ItemsPatrimonio item);
    public void registroSaida(ItemsPatrimonio item, int quantidade);
    public void itemDanificado(ItemsPatrimonio item);
    public void mudarStatus(ItemsPatrimonio item, Status status); // itemDanificado seria a mesma coisa, ajustar no futuro
    public void transferencia(ItemsPatrimonio item, Departamento departamento);
    public void listagem();
    public void relatorio();
}
