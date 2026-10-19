package domain;

import java.math.BigInteger;
import java.time.LocalDate;

/**
 * Representa um atendimento cadastrado no sistema.
 * <p>
 * Cada atendimento deve possuir obrigatoriamente um Serviço que foi realizado.
 * 
 * @see Servico
 */
public class Atendimento {
    private Servico servicoPrestado;
    private final BigInteger idServicoPrestado;
    private LocalDate data;
    private final String dataString;
    private final BigInteger id;
    private final BigInteger idAnimal;
    private Animal animalAtendido;

        /**
         * Construtor padrão da classe.
         * @param idServicoPrestado ,que será linkado posteriormente na classe {@link application.Cadastro}
         * @param data ,que será parseada para LocalDate pela classe {@link application.Cadastro}
         * @param id
         * @param idAnimal
        */
        public Atendimento(BigInteger idServicoPrestado, String data, BigInteger id, BigInteger idAnimal) {
        this.idServicoPrestado = idServicoPrestado;
        this.dataString = data;
        this.id = id;
        this.idAnimal = idAnimal;
    }

    /*-------------Métodos Getters--------------*/
    public LocalDate getData() {
        return data;
    }
    public BigInteger getId() {
        return id;
    }
    public Servico getServicoPrestado() {
        return servicoPrestado;
    }
    public BigInteger getIdServicoPrestado() {
        return idServicoPrestado;
    }
    public BigInteger getIdAnimal() {
        return idAnimal;
    }
    public String getDataString() {
        return dataString;
    }
    public Animal getAnimalAtendido() {
        return animalAtendido;
    }

    /*-------------Métodos Setters--------------*/
    public void setData(LocalDate data) {
        this.data = data;
    }
    public void setAnimalAtendido(Animal animalAtendido) {
        this.animalAtendido = animalAtendido;
    }
    public void setServicoPrestado(Servico servicoPrestado) {
        this.servicoPrestado = servicoPrestado;
    }
}
