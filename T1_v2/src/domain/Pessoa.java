package domain;

import java.math.BigInteger;

/**
 * Classe abstrata para representação de uma pessoa física ou jurídica no sistema.
 * <p>
 * 
 */
public abstract class Pessoa {
    private final String nome;
    private final String endereco;
    private final String telefone;
    private final BigInteger id;

    /**
     * Construtor padrão da classe, herdado pelas subclasses pelo super().
     * @param nome
     * @param endereco
     * @param telefone
     * @param id
    */
    public Pessoa(String nome, String endereco, String telefone, BigInteger id) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.id = id;
    }

    /*-------------Métodos Getters--------------*/
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
