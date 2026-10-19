package domain;

import java.math.BigInteger;

/**
 * Representa um Prestador de Serviços com CNPJ no sistema.
 * <p>
 * Extende a classe {@link application.Pessoa}
 * Deve, obrigatoriamente possuir o campo de CNPJ
*/
public class PessoaJuridica extends Pessoa {
    private final String cnpj;
    /**
     * Construtor padrão da classe.
     * @param nome
     * @param endereco
     * @param telefone
     * @param id
     * @param cnpj
    */
    public PessoaJuridica(String nome, String endereco, String telefone, BigInteger id,String cnpj) {
        super(nome, endereco, telefone, id);
        this.cnpj = cnpj;
    }

    /*-------------Métodos Getters--------------*/
    public String getCnpj() {
        return cnpj;
    }
}
