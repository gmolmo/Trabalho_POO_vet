package domain;

public class PessoaJuridica extends Pessoa {
    private String cnpj;
    
    public PessoaJuridica(String nome, String endereco, String telefone, int id,String cnpj) {
        super(nome, endereco, telefone, id);
        this.cnpj = cnpj;
    }
    public String getCnpj() {
        return cnpj;
    }
}
