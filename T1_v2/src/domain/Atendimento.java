package domain;

import java.math.BigInteger;
import java.time.LocalDate;

public class Atendimento {
    private Servico servicoPrestado;
    private final BigInteger idServicoPrestado;
    private LocalDate data;
    private final String dataString;
    private final BigInteger id;
    private final BigInteger idAnimal;
    private Animal animalAtendido;

        public Atendimento(BigInteger idServicoPrestado, String data, BigInteger id, BigInteger idAnimal) {
        this.idServicoPrestado = idServicoPrestado;
        this.dataString = data;
        this.id = id;
        this.idAnimal = idAnimal;
    }

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

    public void setServicoPrestado(Servico servicoPrestado) {
        this.servicoPrestado = servicoPrestado;
    }

    public BigInteger getIdAnimal() {
        return idAnimal;
    }

    public String getDataString() {
        return dataString;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setAnimalAtendido(Animal animalAtendido) {
        this.animalAtendido = animalAtendido;
    }

    public Animal getAnimalAtendido() {
        return animalAtendido;
    }


}
