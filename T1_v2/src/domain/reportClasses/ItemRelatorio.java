package domain.reportClasses;



/**
 * Classe para representação de um serviço ou produto para o relatório 01.
 * <p>
 */
public class ItemRelatorio {
    private final String nome;
    private double faturamento;
    private double lucro;

    /**
     * Construtor padrão da classe.
     * @param nome
     * @param faturamento
     * @param lucro
    */
    public ItemRelatorio(String nome, double faturamento, double lucro) {
        this.nome = nome;
        this.faturamento = faturamento;
        this.lucro = lucro;
    }

    /*-------------Métodos Getters--------------*/
    public String getNome() {
        return nome;
    }
    public double getFaturamento() {
        return faturamento;
    }
    public double getLucro() {
        return lucro;
    }

    /*-------------Métodos Getters--------------*/
    public void setFaturamento(double faturamento) {
        this.faturamento = faturamento;
    }
    public void setLucro(double lucro) {
        this.lucro = lucro;
    }
}
