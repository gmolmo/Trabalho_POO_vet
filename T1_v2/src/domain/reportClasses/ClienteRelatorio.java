package domain.reportClasses;

import domain.Atendimento;
import domain.Venda;
import java.util.ArrayList;

/**
 * Classe para representação de uma pessoa física cliente para o relatório 03.
 * <p>
 * Possui uma lista de compras realizadas pelo cliente.
 */
public class ClienteRelatorio {
    private final String nome;
    private double totalGasto;
    private ArrayList<Venda> comprasFeitas;
    private ArrayList<Atendimento> atendimentosFeitos;

    /**
     * Construtor padrão
     * @param nome
     * @param totalGasto
     * @param comprasFeitas
     * @param atendimentosFeitos
    */
    public ClienteRelatorio(String nome, double totalGasto, ArrayList<Venda> comprasFeitas,
            ArrayList<Atendimento> atendimentosFeitos) {
        this.nome = nome;
        this.totalGasto = totalGasto;
        this.comprasFeitas = comprasFeitas;
        this.atendimentosFeitos = atendimentosFeitos;
    }

    /*-------------Métodos Getters--------------*/
    public String getNome() {
        return nome;
    }
    public double getTotalGasto() {
        return totalGasto;
    }
    public ArrayList<Venda> getComprasFeitas() {
        return comprasFeitas;
    }
    public ArrayList<Atendimento> getAtendimentosFeitos() {
        return atendimentosFeitos;
    }

    /*-------------Métodos Setters--------------*/
    public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }
    public void setComprasFeitas(ArrayList<Venda> comprasFeitas) {
        this.comprasFeitas = comprasFeitas;
    }
    public void setAtendimentosFeitos(ArrayList<Atendimento> atendimentosFeitos) {
        this.atendimentosFeitos = atendimentosFeitos;
    }
}
