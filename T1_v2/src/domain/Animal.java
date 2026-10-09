package domain;

import java.time.LocalDate;

public class Animal {
    private PessoaFisica tutor;
    private String nome;
    private LocalDate dataNascimento;
    private String familia; 
    private int id;

    public PessoaFisica getTutor() {
        return tutor;
    }
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
}
