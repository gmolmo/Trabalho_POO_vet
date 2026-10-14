package domain;

public class Servico {
    private String nome;
    private double preco;
    private Animal animalAtendido;
    private Pessoa prestadorServico;
    private int id;
    
    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }
    public Animal getAnimalAtendido() {
        return animalAtendido;
    }
    public Pessoa getPrestadorServico() {
        return prestadorServico;
    }
    public int getId() {
        return id;
    }
}
