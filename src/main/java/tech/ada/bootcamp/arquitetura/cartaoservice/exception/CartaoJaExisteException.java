package tech.ada.bootcamp.arquitetura.cartaoservice.exception;

public class CartaoJaExisteException extends RuntimeException {
    public CartaoJaExisteException(String message) {super(message); }

    public CartaoJaExisteException(String message, Throwable cause) {
        super(message, cause);
    }

}
