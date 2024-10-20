package br.com.postechfiap.locadoraveiculosfiap.exceptions;

public class VeiculoNotFoundException extends RuntimeException {

    public VeiculoNotFoundException(Long id) {
        super("Veiculo com id " + id + " não encontrado");
    }

    public VeiculoNotFoundException(String message) {
        super(message);
    }
}
