package br.com.postechfiap.locadoraveiculosfiap.dto;

import br.com.postechfiap.locadoraveiculosfiap.entities.Endereco;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDto {

    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;


    public static EnderecoDto from(Endereco endereco) {
        return EnderecoDto.builder()
                .id(endereco.getId())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .build();
    }

    public static List<EnderecoDto> from(List<Endereco> enderecos) {
        return enderecos.stream().map(EnderecoDto::from).toList();
    }

    public static Endereco to(EnderecoDto enderecoDto) {
        return Endereco.builder()
                .id(enderecoDto.getId())
                .logradouro(enderecoDto.getLogradouro())
                .numero(enderecoDto.getNumero())
                .complemento(enderecoDto.getComplemento())
                .bairro(enderecoDto.getBairro())
                .cidade(enderecoDto.getCidade())
                .estado(enderecoDto.getEstado())
                .cep(enderecoDto.getCep())
                .build();
    }

    public static List<Endereco> to(List<EnderecoDto> enderecoDtos) {
        return enderecoDtos.stream().map(EnderecoDto::to).toList();
    }
}
