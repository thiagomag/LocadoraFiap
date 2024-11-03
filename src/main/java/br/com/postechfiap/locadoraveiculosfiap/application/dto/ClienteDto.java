package br.com.postechfiap.locadoraveiculosfiap.application.dto;

import br.com.postechfiap.locadoraveiculosfiap.domain.model.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Cliente", description = "Representa um cliente", example = "{\n" +
        "  \"codCliente\": 123,\n" +
        "  \"cnh\": \"1234567890\",\n" +
        "  \"email\": \"exemplo@dominio.com\",\n" +
        "  \"nome\": \"João da Silva\",\n" +
        "  \"cpf\": \"123.456.789-00\",\n" +
        "}\n")
public class ClienteDto {

    private Long codCliente;
    private String cnh;
    private String email;
    private String nome;
    private String cpf;
    private List<EnderecoDto> enderecos;
    private List<ReservaDto> reservas;


    public static ClienteDto from(Cliente cliente) {
        return ClienteDto.builder()
                .codCliente(cliente.getId())
                .cnh(cliente.getCnh())
                .email(cliente.getEmail())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .enderecos(EnderecoDto.from(cliente.getEnderecos()))
                .reservas(ReservaDto.from(cliente.getReservas()))
                .build();
    }

    public static Cliente to(ClienteDto clienteDto) {
        return Cliente.builder()
                .id(clienteDto.getCodCliente())
                .cnh(clienteDto.getCnh())
                .email(clienteDto.getEmail())
                .nome(clienteDto.getNome())
                .cpf(clienteDto.getCpf())
                .enderecos(EnderecoDto.to(clienteDto.getEnderecos()))
                .reservas(ReservaDto.to(clienteDto.getReservas()))
                .build();
    }
}
