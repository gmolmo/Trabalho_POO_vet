package domain;

import java.time.LocalDate;

public class Produto {
    private int id;
    private String nome;
    private double precoCompra;
    private double precoVenda;
    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;
    private Animal[] animaisDestinados;

    public Produto(int id, String nome, double precoCompra, double precoVenda, LocalDate inicioVigencia, LocalDate fimVigencia, Animal[] animaisDestinados) {
        this.id = id;
        this.nome = nome;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
        this.animaisDestinados = animaisDestinados;
    }

    public String getNome()
    {
        return this.nome;
    }
}