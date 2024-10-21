package utils;

import br.com.postechfiap.locadoraveiculosfiap.dto.EnderecoDto;
import br.com.postechfiap.locadoraveiculosfiap.entities.Endereco;

public class EnderecoTestUtil {

    public static Endereco buildEndereco() {
        return
            Endereco.builder()
                .id(1L)
                .logradouro("Rua dos Bobos")
                .numero(String.valueOf(0))
                .complemento("Casa")
                .bairro("Vila do Chaves")
                .cidade("São Paulo")
                .estado("SP")
                .cep("00000-000")
                .build();
    }

    public static EnderecoDto buildEnderecoDto() {
        return
            EnderecoDto.builder()
                .id(1L)
                .logradouro("Rua dos Bobos")
                .numero(String.valueOf(0))
                .complemento("Casa")
                .bairro("Vila do Chaves")
                .cidade("São Paulo")
                .estado("SP")
                .cep("00000-000")
                .build();
    }
}
