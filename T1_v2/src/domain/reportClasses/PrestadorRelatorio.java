package domain.reportClasses;

/**
 * Classe para representação de uma pessoa física ou jurídica prestadora de serviço para o relatório 02.
 * <p>
 * Possui uma lista de serviços realizados (ou não?) pelo prestador.
 */
public class PrestadorRelatorio {
    private final String tipo;
    private final String nome;
    private double valorRecebido;
    private String[] servicosExecutados;
    /**
     * Construtor padrão da classe.
     * @param tipo
     * @param nome
     * @param valorRecebido
     * @param servicosExecutados
    */
    public PrestadorRelatorio(String tipo, String nome, double valorRecebido, String[] servicosExecutados) {
        this.tipo = tipo;
        this.nome = nome;
        this.valorRecebido = valorRecebido;
        this.servicosExecutados = servicosExecutados;
    }

    /*-------------Métodos Getters--------------*/
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

    /*-------------Métodos Setters--------------*/
    public void setValorRecebido(double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }
    public void setServicosExecutados(String[] servicosExecutados) {
        this.servicosExecutados = servicosExecutados;
    }
}
