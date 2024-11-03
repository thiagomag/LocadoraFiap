package br.com.postechfiap.locadoraveiculosfiap.application.handler.exceptions;

public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException(Long id) {
        super("Cliente não encontrado com o id: " + id);
    }
}
