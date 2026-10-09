package domain;

public class Serviço {
    private String nome;
    private double preco;
    private Animal animalAtendido;
    private Pessoa prestadorServiço;
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
    public Pessoa getPrestadorServiço() {
        return prestadorServiço;
    }
    public int getId() {
        return id;
    }
}
