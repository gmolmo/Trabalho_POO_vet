package domain;

import java.math.BigInteger;
import java.time.LocalDate;

/**
 * Representa um animal de estimação cadastrado no sistema.
 * <p>
 * Cada animal deve pertencer obrigatoriamente a uma das quatro famílias suportadas:
 * Cães, Felinos, Aves ou Roedores, e estar associado a um tutor (Pessoa Física).
 * 
 * @see PessoaFisica
 */
public class Animal {
    private final BigInteger idTutor;
    private PessoaFisica tutor;
    private final String nome;
    private LocalDate dataNascimento;
    private final String dataString;
    private final String familia; 
    private final BigInteger id;
        /**
         * Construtor padrão da classe.
         * <p>
         * @param idTutor ,que será linkado no processaAnimal da classe {@link application.Cadastro}
         * @param nome
         * @param data ,que será parseada para LocalDate pela classe {@link application.Cadastro}
         * @param familia
         * @param id
         */
        public Animal(BigInteger idTutor, String nome, String data, String familia, BigInteger id) {
        this.idTutor = idTutor;
        this.nome = nome;
        this.dataString = data;
        this.familia = familia;
        this.id = id;
    }

    /*-------------Métodos Getters--------------*/

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
    public String getDataString() {
        return dataString;
    }
    public PessoaFisica getTutor() {
        return tutor;
    }

    /*-------------Métodos Setters--------------*/
    public void setTutor(PessoaFisica tutor) {
        this.tutor = tutor;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
