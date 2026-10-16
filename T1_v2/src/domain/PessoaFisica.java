package domain;

import java.math.BigInteger;
import java.time.LocalDate;

public class PessoaFisica extends Pessoa {
    private final String cpf;
    private LocalDate dataNascimento;
    private final String dataString;
    
    public PessoaFisica(String nome, String endereco, String telefone, BigInteger id,String cpf, String dataString) {
        super(nome, endereco, telefone, id);
        this.cpf = cpf;
        this.dataString = dataString;
    }
    public String getCpf() {
        return cpf;
    }
    public LocalDate getDataNascimento(){
        return this.dataNascimento;
    }

    public String getDataString() {
        return dataString;
    }

    public void setData(LocalDate data)
    {
        this.dataNascimento = data;
    }

}
