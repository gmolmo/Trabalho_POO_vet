package domain;

import java.time.LocalDate;

public class Atendimento {
    private Servico servicoPrestado;
    private LocalDate data;
    private int id;
    
    public Servico getServidoPrestado() {
        return servicoPrestado;
    }

    public LocalDate getData() {
        return data;
    }

    public int getId() {
        return id;
    }

    public Atendimento(Servico servicoPrestado, LocalDate data, int id) {
        this.servicoPrestado = servicoPrestado;
        this.data = data;
        this.id = id;
    };
}
