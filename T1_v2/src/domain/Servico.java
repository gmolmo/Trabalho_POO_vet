package domain;

import java.time.LocalDate;

public class Servico {
    private final String nome;
    private final double preco;
    private final String[] animaisDestinados;
    private final int idPrestadorServico;
    private Pessoa PrestadorServico;
    private final int id;
    LocalDate dataInicio;
    LocalDate datafim;

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
    public int getIdPrestadorServico() {
        return idPrestadorServico;
    }
    public int getId() {
        return id;
    }   
    public Pessoa getPrestadorServico() {
        return PrestadorServico;
    }

    public void setPrestadorServico(Pessoa prestadorServico) {
        PrestadorServico = prestadorServico;
    }
}
