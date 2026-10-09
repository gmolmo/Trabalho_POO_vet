package domain;

import java.time.LocalDate;

public class Produto {
    private int id;
    private String nome;
    private double precoCompra;
    private double precoVenda;
    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;
    private String[] animaisDestinados;

    public Produto(int id, String nome, double precoCompra, double precoVenda, LocalDate inicioVigencia, LocalDate fimVigencia, String[] animaisDestinados) {
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
    public double getPrecoCompra()
    {
        return this.precoCompra;
    }
    public double getPrecoVenda()
    {
        return this.precoVenda;
    }

    public int getId() {
        return id;
    }

    public LocalDate getInicioVigencia() {
        return inicioVigencia;
    }

    public LocalDate getFimVigencia() {
        return fimVigencia;
    }

    public String[] getAnimaisDestinados() {
        return animaisDestinados;
    }
}