package domain;

public class PrestadorRelatorio {
    private final String tipo;
    private final String nome;
    private double valorRecebido;
    private String[] servicosExecutados;
    public PrestadorRelatorio(String tipo, String nome, double valorRecebido, String[] servicosExecutados) {
        this.tipo = tipo;
        this.nome = nome;
        this.valorRecebido = valorRecebido;
        this.servicosExecutados = servicosExecutados;
    }
    public String getTipo() {
        return tipo;
    }
    public String getNome() {
        return nome;
    }
    public double getValorRecebido() {
        return valorRecebido;
    }
    public String[] getServicosExecutados() {
        return servicosExecutados;
    }
    public void setValorRecebido(double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }
    public void setServicosExecutados(String[] servicosExecutados) {
        this.servicosExecutados = servicosExecutados;
    }
}
