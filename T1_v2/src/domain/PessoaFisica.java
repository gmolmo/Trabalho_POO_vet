package domain;

public class PessoaFisica extends Pessoa {
    private String cpf;
    
    public PessoaFisica(String nome, String endereco, String telefone, int id,String cpf) {
        super(nome, endereco, telefone, id);
        this.cpf = cpf;
    }
    public String getCpf() {
        return cpf;
    }
}
