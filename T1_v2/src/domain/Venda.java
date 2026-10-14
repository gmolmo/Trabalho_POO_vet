package domain;

import java.time.LocalDate;

public class Venda {
    private PessoaFisica cliente;
    private int idCliente;
    private Produto produtoVendido;
    private int idProduto;
    private int quantidade;
    private int id;
    private LocalDate dataVenda;


    public PessoaFisica getCliente() {
        return cliente;
    }

    public Produto getProdutoVendido() {
        return produtoVendido;
    }

    public Venda(int idCliente, int idProduto, int quantidade, int id, LocalDate dataVenda) {
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.id = id;
        this.dataVenda = dataVenda;
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

    
}
