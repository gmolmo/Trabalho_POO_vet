package domain;

import java.math.BigInteger;
import java.time.LocalDate;

/**
 * Representa uma venda cadastrada no sistema.
 * <p>
 * Cada atendimento deve possuir obrigatoriamente um Produto que foi vendido, e um Cliente que fez a compra.
 * 
 * @see Produto 
 * @see PessoaFisica
 * */
public class Venda {
    private PessoaFisica cliente;
    private final BigInteger idCliente;
    private Produto produtoVendido;
    private final BigInteger idProduto;
    private final int quantidade;
    private final BigInteger id;
    private final String dataString;
    private LocalDate dataVenda;

    /**
     * 
     * @param idCliente ,que será linkado posteriormente na classe {@link application.Cadastro}
     * @param idProduto ,que será linkado posteriormente na classe {@link application.Cadastro}
     * @param quantidade
     * @param id
     * @param dataVenda ,que será parseada para LocalDate pela classe {@link application.Cadastro}
    */
    public Venda(BigInteger idCliente, BigInteger idProduto, int quantidade, BigInteger id, String dataVenda) {
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.id = id;
        this.dataString= dataVenda;
    }

    /*-------------Métodos Getters--------------*/
    public PessoaFisica getCliente() {
        return cliente;
    }
    public Produto getProdutoVendido() {
        return produtoVendido;
    }
    public BigInteger getIdCliente() {
        return idCliente;
    }
    public BigInteger getIdProduto() {
        return idProduto;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public BigInteger getId() {
        return id;
    }
    public LocalDate getDataVenda() {
        return dataVenda;
    }
    public String getDataString() {
        return dataString;
    }

    /*-------------Métodos Setters--------------*/
    public void setCliente(PessoaFisica cliente) {
        this.cliente = cliente;
    }
    public void setProdutoVendido(Produto produtoVendido) {
        this.produtoVendido = produtoVendido;
    }
    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
  
}
