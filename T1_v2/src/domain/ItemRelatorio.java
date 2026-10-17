package domain;

public class ItemRelatorio {
    private final String nome;
    private double faturamento;
    private double lucro;

    public ItemRelatorio(String nome, double faturamento, double lucro) {
        this.nome = nome;
        this.faturamento = faturamento;
        this.lucro = lucro;
    }

    public String getNome() {
        return nome;
    }

    public void setFaturamento(double faturamento) {
        this.faturamento = faturamento;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }

    public double getFaturamento() {
        return faturamento;
    }

    public double getLucro() {
        return lucro;
    }
    
}
