package domain;

import java.time.LocalDate;

public class Venda {
    private PessoaFisica cliente;
    private Produto produtoVendido;
    private int quantidade;
    private int id;
    private LocalDate dataVenda;

    public Venda(PessoaFisica cliente, Produto produtoVendido, int quantidade, int id, LocalDate dataVenda) {
        this.cliente = cliente;
        this.produtoVendido = produtoVendido;
        this.quantidade = quantidade;
        this.id = id;
        this.dataVenda = dataVenda;
    }

    public PessoaFisica getCliente() {
        return cliente;
    }

    public Produto getProdutoVendido() {
        return produtoVendido;
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
