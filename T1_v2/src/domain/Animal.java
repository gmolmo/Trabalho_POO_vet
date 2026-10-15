package domain;

import java.time.LocalDate;

public class Animal {
    private final int idTutor;
    private final String nome;
    private LocalDate dataNascimento;
    private final String dataString;
    private final String familia; 
    private final int id;

        public Animal(int idTutor, String nome, String data, String familia, int id) {
        this.idTutor = idTutor;
        this.nome = nome;
        this.dataString = data;
        this.familia = familia;
        this.id = id;
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
    public int getIdTutor() {
        return idTutor;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDataString() {
        return dataString;
    }
}
