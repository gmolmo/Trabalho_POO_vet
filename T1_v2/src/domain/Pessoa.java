package domain;

public abstract class Pessoa {
    public String nome;
    public String endereco;
    public String telefone;
    public int id;

    public Pessoa(String nome, String endereco, String telefone, int id) {
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
    public int getId() {
        return id;
    }
}
