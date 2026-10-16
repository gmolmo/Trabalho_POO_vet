package domain;

import java.math.BigInteger;

public class PessoaJuridica extends Pessoa {
    private final String cnpj;
    
    public PessoaJuridica(String nome, String endereco, String telefone, BigInteger id,String cnpj) {
        super(nome, endereco, telefone, id);
        this.cnpj = cnpj;
    }
    public String getCnpj() {
        return cnpj;
    }
}
