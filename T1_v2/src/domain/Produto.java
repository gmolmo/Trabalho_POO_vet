package domain;

import java.math.BigInteger;
import java.time.LocalDate;

/**
 * Representa um Produto cadastrado no sistema.
 * <p>
 * Possui um período de vigência definido por:
 * Uma data de início do período
 * Uma data de fim do período de Vigência
 * Cada Produto deve atender obrigatoriamente a uma das quatro famílias suportadas:
 * Cães, Felinos, Aves ou Roedores.
 */
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

    /**
     * Construtor padrão da classe
     * @param id
     * @param nome
     * @param precoCompra
     * @param precoVenda
     * @param inicioVigencia ,que será parseada para LocalDate pela classe {@link application.Cadastro}
     * @param fimVigencia ,que será parseada para LocalDate pela classe {@link application.Cadastro}
     * @param animaisDestinados
    */
    public Produto(BigInteger id, String nome, double precoCompra, double precoVenda, String inicioVigencia, String fimVigencia, String[] animaisDestinados) {
        this.id = id;
        this.nome = nome;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.datainiString = inicioVigencia;
        this.datafimString = fimVigencia;
        this.animaisDestinados = animaisDestinados;
    }

    /*-------------Métodos Getters--------------*/
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

    /*-------------Métodos Setters--------------*/
    public void setInicioVigencia(LocalDate inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }
    public void setFimVigencia(LocalDate fimVigencia) {
        this.fimVigencia = fimVigencia;
    }
}