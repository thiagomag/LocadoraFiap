package utils;

import br.com.postechfiap.locadoraveiculosfiap.application.dto.ClienteDto;
import br.com.postechfiap.locadoraveiculosfiap.domain.model.Cliente;

import java.util.Collections;

public class ClienteTestUtil {

    public static Cliente buildCliente() {
        return Cliente.builder()
                .id(1L)
                .nome("João")
                .cpf("12345678901")
                .cnh("12345678901")
                .email("teste@teste.com")
                .enderecos(Collections.singletonList(EnderecoTestUtil.buildEndereco()))
                .reservas(Collections.singletonList(ReservaTestUtil.buildReserva()))
                .build();
    }

    public static ClienteDto buildClienteDto() {
        return ClienteDto.builder()
                .codCliente(1L)
                .nome("João")
                .cpf("12345678901")
                .cnh("12345678901")
                .email("teste@teste.com")
                .enderecos(Collections.singletonList(EnderecoTestUtil.buildEnderecoDto()))
                .reservas(Collections.singletonList(ReservaTestUtil.buildReservaDto()))
                .build();
    }
}
