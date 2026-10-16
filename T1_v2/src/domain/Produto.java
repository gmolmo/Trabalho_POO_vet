package domain;

import java.math.BigInteger;
import java.time.LocalDate;

public class Produto {
    private final BigInteger id;
    private final String nome;
    private final double precoCompra;
    private final double precoVenda;
    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;
    private final String datainiString;
    private final String datafimString;
    private final String[] animaisDestinados;

    public Produto(BigInteger id, String nome, double precoCompra, double precoVenda, String inicioVigencia, String fimVigencia, String[] animaisDestinados) {
        this.id = id;
        this.nome = nome;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.datainiString = inicioVigencia;
        this.datafimString = fimVigencia;
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

    public BigInteger getId() {
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

    public String getDatainiString() {
        return datainiString;
    }

    public String getDatafimString() {
        return datafimString;
    }

    public void setInicioVigencia(LocalDate inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public void setFimVigencia(LocalDate fimVigencia) {
        this.fimVigencia = fimVigencia;
    }
}