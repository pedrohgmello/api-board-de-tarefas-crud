package pedrohgmello.com.github.to_do_list_spring.exceptions;

public class AcessoNaoAutorizadoException extends RuntimeException {
    public AcessoNaoAutorizadoException(String message) {
        super(message);
    }
}
