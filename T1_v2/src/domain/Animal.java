package domain;

import java.time.LocalDate;

public class Animal {
    private int idTutor;
    private String nome;
    private LocalDate dataNascimento;
    private String familia; 
    private int id;

    public String getNome() {
        return nome;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public String getFamilia() {
        return familia;
    }
    public int getId() {
        return id;
    }
    public Animal(int idTutor, String nome, LocalDate dataNascimento, String familia, int id) {
        this.idTutor = idTutor;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.familia = familia;
        this.id = id;
    }
    public int getIdTutor() {
        return idTutor;
    }
}
