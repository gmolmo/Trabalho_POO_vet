package domain;

import java.math.BigInteger;
import java.time.LocalDate;

/**
 * Representa uma pessoa com CPF no sistema.
 * <p>
 * Extende a classe {@link application.Pessoa}
 * Deve, obrigatoriamente possuir o campo de CPF e uma data de nascimento
 * Pode representar um Cliente ou um Prestador de Serviços
*/
public class PessoaFisica extends Pessoa {
    private final String cpf;
    private LocalDate dataNascimento;
    private final String dataString;
    
    /**
     * Construtor padrão da classe.
     * @param nome
     * @param endereco
     * @param telefone
     * @param id
     * @param cpf
     * @param dataString ,que será parseada para LocalDate pela classe {@link application.Cadastro}
    */
    public PessoaFisica(String nome, String endereco, String telefone, BigInteger id,String cpf, String dataString) {
        super(nome, endereco, telefone, id);
        this.cpf = cpf;
        this.dataString = dataString;
    }

    /*-------------Métodos Getters--------------*/
    public String getCpf() {
        return cpf;
    }
    public LocalDate getDataNascimento(){
        return this.dataNascimento;
    }

    public String getDataString() {
        return dataString;
    }

    /*-------------Métodos Setters--------------*/
    public void setData(LocalDate data)
    {
        this.dataNascimento = data;
    }

}
