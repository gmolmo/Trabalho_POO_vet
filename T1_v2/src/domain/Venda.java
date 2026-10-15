package domain;

import java.time.LocalDate;

public class Venda {
    private PessoaFisica cliente;
    private final int idCliente;
    private Produto produtoVendido;
    private final int idProduto;
    private final int quantidade;
    private final int id;
    private final String dataString;
    private LocalDate dataVenda;

    public Venda(int idCliente, int idProduto, int quantidade, int id, String dataVenda) {
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

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getId() {
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
