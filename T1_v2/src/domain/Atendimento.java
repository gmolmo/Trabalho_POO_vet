package domain;

import java.time.LocalDate;

public class Atendimento {
    private Servico servicoPrestado;
    private final int idServicoPrestado;
    private LocalDate data;
    private final String dataString;
    private final int id;
    private final int idAnimal;

        public Atendimento(int idServicoPrestado, String data, int id, int idAnimal) {
        this.idServicoPrestado = idServicoPrestado;
        this.dataString = data;
        this.id = id;
        this.idAnimal = idAnimal;
    }
    
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

    public void setServicoPrestado(Servico servicoPrestado) {
        this.servicoPrestado = servicoPrestado;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public String getDataString() {
        return dataString;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }


}
