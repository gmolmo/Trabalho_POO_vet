package domain;

import java.time.LocalDate;

public class PessoaFisica extends Pessoa {
    private final String cpf;
    private final LocalDate dataNascimento;
    
    public PessoaFisica(String nome, String endereco, String telefone, int id,String cpf, LocalDate dataNascimento) {
        super(nome, endereco, telefone, id);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }
    public String getCpf() {
        return cpf;
    }
    public LocalDate getDataNascimento(){
        return this.dataNascimento;
    }
}
