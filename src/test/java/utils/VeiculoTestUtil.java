package utils;

import br.com.postechfiap.locadoraveiculosfiap.dto.VeiculoDto;
import br.com.postechfiap.locadoraveiculosfiap.entities.Veiculo;
import br.com.postechfiap.locadoraveiculosfiap.enuns.CategoriaVeiculosEnum;

public class VeiculoTestUtil {

    public static Veiculo buildVeiculo() {
        return Veiculo.builder()
                .id(1L)
                .marca("Chevrolet")
                .modelo("Onix")
                .ano(String.valueOf(2021))
                .placa("ABC1234")
                .cor("Branco")
                .chassi("123456789")
                .renavam("987654321")
                .combustivel("Flex")
                .categoria(CategoriaVeiculosEnum.ECONOMICO)
                .valorDiaria("100")
                .quilometragem(1000L)
                .disponivel(true)
                .build();
    }

    public static VeiculoDto buildVeiculoDto() {
        return VeiculoDto.builder()
                .marca("Chevrolet")
                .modelo("Onix")
                .ano("2020")
                .placa("ABC1234")
                .cor("Branco")
                .chassi("123456789")
                .renavam("987654321")
                .combustivel("Flex")
                .categoria(CategoriaVeiculosEnum.ECONOMICO)
                .valorDiaria("100")
                .quilometragem(1000L)
                .disponivel(true)
                .build();
    }

    public static VeiculoDto buildVeiculoDtoToUpdate() {
        return VeiculoDto.builder()
                .modelo("Novo Modelo")
                .quilometragem(5000L)
                .build();
    }
}
