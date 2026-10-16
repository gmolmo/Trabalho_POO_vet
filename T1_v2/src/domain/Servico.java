package domain;

import java.math.BigInteger;
import java.time.LocalDate;

public class Servico {
    private final String nome;
    private final double preco;
    private final String[] animaisDestinados;
    private final BigInteger idPrestadorServico;
    private Pessoa PrestadorServico;
    private final BigInteger id;
    private LocalDate dataInicio;
    private LocalDate datafim;
    private final String datainiString;
    private final String datafimString;

    public Servico(String nome, double preco, String[] animaisDestinados, BigInteger idPrestadorServico, BigInteger id,
       String dataInicio, String datafim) {
        this.nome = nome;
        this.preco = preco;
        this.animaisDestinados = animaisDestinados;
        this.idPrestadorServico = idPrestadorServico;
        this.id = id;
        this.datainiString = dataInicio;
        this.datafimString = datafim;
    }
    
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public LocalDate getDatafim() {
        return datafim;
    }
    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }
    public String[] getAnimaisDestinados() {
        return animaisDestinados;
    }
    public BigInteger getIdPrestadorServico() {
        return idPrestadorServico;
    }
    public BigInteger getId() {
        return id;
    }   
    public Pessoa getPrestadorServico() {
        return PrestadorServico;
    }

    public void setPrestadorServico(Pessoa prestadorServico) {
        PrestadorServico = prestadorServico;
    }

    public String getDatainiString() {
        return datainiString;
    }

    public String getDatafimString() {
        return datafimString;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDatafim(LocalDate datafim) {
        this.datafim = datafim;
    }
}
