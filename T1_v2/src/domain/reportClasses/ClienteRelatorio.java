package domain.reportClasses;

import java.util.ArrayList;

import domain.Atendimento;
import domain.Venda;

public class ClienteRelatorio {
    private final String nome;
    private double totalGasto;
    private ArrayList<Venda> comprasFeitas;
    private ArrayList<Atendimento> atendimentosFeitos;

    public ClienteRelatorio(String nome, double totalGasto, ArrayList<Venda> comprasFeitas,
            ArrayList<Atendimento> atendimentosFeitos) {
        this.nome = nome;
        this.totalGasto = totalGasto;
        this.comprasFeitas = comprasFeitas;
        this.atendimentosFeitos = atendimentosFeitos;
    }
        public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }
    public void setComprasFeitas(ArrayList<Venda> comprasFeitas) {
        this.comprasFeitas = comprasFeitas;
    }
    public void setAtendimentosFeitos(ArrayList<Atendimento> atendimentosFeitos) {
        this.atendimentosFeitos = atendimentosFeitos;
    }
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
}
