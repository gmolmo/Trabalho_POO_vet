package domain;

import java.math.BigInteger;
import java.time.LocalDate;

public class Venda {
    private PessoaFisica cliente;
    private final BigInteger idCliente;
    private Produto produtoVendido;
    private final BigInteger idProduto;
    private final int quantidade;
    private final BigInteger id;
    private final String dataString;
    private LocalDate dataVenda;

    public Venda(BigInteger idCliente, BigInteger idProduto, int quantidade, BigInteger id, String dataVenda) {
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.id = id;
        this.dataString= dataVenda;
    }

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

    public void setCliente(PessoaFisica cliente) {
        this.cliente = cliente;
    }

    public void setProdutoVendido(Produto produtoVendido) {
        this.produtoVendido = produtoVendido;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    
}
