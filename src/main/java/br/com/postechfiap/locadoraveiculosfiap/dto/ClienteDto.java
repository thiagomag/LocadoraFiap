package br.com.postechfiap.locadoraveiculosfiap.dto;

import br.com.postechfiap.locadoraveiculosfiap.entities.Cliente;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
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
