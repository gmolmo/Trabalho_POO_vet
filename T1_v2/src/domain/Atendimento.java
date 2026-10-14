package domain;

import java.time.LocalDate;

public class Atendimento {
    private Servico servicoPrestado;
    public Atendimento(int idServicoPrestado, LocalDate data, int id, int idAnimal) {
        this.idServicoPrestado = idServicoPrestado;
        this.data = data;
        this.id = id;
        this.idAnimal = idAnimal;
    }

    private int idServicoPrestado;
    private LocalDate data;
    private int id;
    private int idAnimal;
    
    public Servico getServidoPrestado() {
        return servicoPrestado;
    }

    public LocalDate getData() {
        return data;
    }

    public int getId() {
        return id;
    }

    public Servico getServicoPrestado() {
        return servicoPrestado;
    }

    public int getIdServicoPrestado() {
        return idServicoPrestado;
    }


}
