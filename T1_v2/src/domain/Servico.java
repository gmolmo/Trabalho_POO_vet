package domain;

import java.time.LocalDate;

public class Servico {
    private String nome;
    private double preco;
    private String[] animaisDestinados;
    private int idPrestadorServico;
    private int id;
    LocalDate dataInicio;
    LocalDate datafim;
    
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

    public Servico(String nome, double preco, String[] animaisDestinados, int idPrestadorServico, int id,
            LocalDate dataInicio, LocalDate datafim) {
        this.nome = nome;
        this.preco = preco;
        this.animaisDestinados = animaisDestinados;
        this.idPrestadorServico = idPrestadorServico;
        this.id = id;
        this.dataInicio = dataInicio;
        this.datafim = datafim;
    }
    public String[] getAnimaisDestinados() {
        return animaisDestinados;
    }
    public int getIdPrestadorServico() {
        return idPrestadorServico;
    }
    public int getId() {
        return id;
    }
}
