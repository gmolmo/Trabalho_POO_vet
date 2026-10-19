package application;

/**
 * Representa uma exceção qualquer capturada durante as etapas de validação dos construtores
*/
public class InconsistenciaException extends Exception{
    public InconsistenciaException(String mensagem){
        super(mensagem);
    }
}
