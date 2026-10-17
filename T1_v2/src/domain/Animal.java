package domain;

import java.math.BigInteger;
import java.time.LocalDate;

public class Animal {
    private final BigInteger idTutor;
    private PessoaFisica tutor;
    private final String nome;
    private LocalDate dataNascimento;
    private final String dataString;
    private final String familia; 
    private final BigInteger id;

        public Animal(BigInteger idTutor, String nome, String data, String familia, BigInteger id) {
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
    public BigInteger getId() {
        return id;
    }
    public BigInteger getIdTutor() {
        return idTutor;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDataString() {
        return dataString;
    }

    public PessoaFisica getTutor() {
        return tutor;
    }

    public void setTutor(PessoaFisica tutor) {
        this.tutor = tutor;
    }
}
