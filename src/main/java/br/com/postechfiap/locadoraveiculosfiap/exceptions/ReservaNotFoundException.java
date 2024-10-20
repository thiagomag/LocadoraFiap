package br.com.postechfiap.locadoraveiculosfiap.exceptions;

public class ReservaNotFoundException extends RuntimeException {

    public ReservaNotFoundException(Long id) {
        super("Reserva com id " + id + " não encontrada");
    }

    public ReservaNotFoundException(String message) {
        super(message);
    }
}
