package tech.ada.bootcamp.arquitetura.cartaoservice.exception;

public class CartaoNaoEncontradoException extends Throwable {

    public CartaoNaoEncontradoException(String message) {super(message); }

    public CartaoNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

}
