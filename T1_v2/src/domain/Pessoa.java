package domain;

import java.math.BigInteger;

public abstract class Pessoa {
    private final String nome;
    private final String endereco;
    private final String telefone;
    private final BigInteger id;

    public Pessoa(String nome, String endereco, String telefone, BigInteger id) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public String getTelefone() {
        return telefone;
    }
    public BigInteger getId() {
        return id;
    }
}
