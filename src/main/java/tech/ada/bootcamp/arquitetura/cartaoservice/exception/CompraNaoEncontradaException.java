package tech.ada.bootcamp.arquitetura.cartaoservice.exception;

public class CompraNaoEncontradaException extends RuntimeException {
    public CompraNaoEncontradaException(String message) {
        super(message);
    }

    public CompraNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }

}
