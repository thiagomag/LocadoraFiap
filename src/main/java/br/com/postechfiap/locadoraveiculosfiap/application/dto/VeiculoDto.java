package br.com.postechfiap.locadoraveiculosfiap.application.dto;

import br.com.postechfiap.locadoraveiculosfiap.domain.model.Veiculo;
import br.com.postechfiap.locadoraveiculosfiap.shared.enuns.CategoriaVeiculosEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Veiculo", description = "Representa um veículo", example = "{\n" +
        "  \"id\": 123,\n" +
        "  \"marca\": \"Fiat\",\n" +
        "  \"modelo\": \"Uno\",\n" +
        "  \"placa\": \"ABC-1234\",\n" +
        "  \"cor\": \"Branco\",\n" +
        "  \"ano\": \"2021\",\n" +
        "  \"chassi\": \"1234567890\",\n" +
        "  \"renavam\": \"1234567890\",\n" +
        "  \"combustivel\": \"Gasolina\",\n" +
        "  \"categoria\": \"SEDAN\",\n" +
        "  \"valorDiaria\": 100.00,\n" +
        "  \"quilometragem\": 1000,\n" +
        "  \"disponivel\": true,\n" +
        "}\n")
public class VeiculoDto {

    private Long id;
    private String marca;
    private String modelo;
    private String placa;
    private String cor;
    private String ano;
    private String chassi;
    private String renavam;
    private String combustivel;
    private CategoriaVeiculosEnum categoria;
    private String valorDiaria;
    private Long quilometragem;
    private Boolean disponivel;

    public static VeiculoDto from(Veiculo veiculo) {
        return VeiculoDto.builder()
                .id(veiculo.getId())
                .marca(veiculo.getMarca())
                .modelo(veiculo.getModelo())
                .placa(veiculo.getPlaca())
                .cor(veiculo.getCor())
                .ano(veiculo.getAno())
                .chassi(veiculo.getChassi())
                .renavam(veiculo.getRenavam())
                .combustivel(veiculo.getCombustivel())
                .categoria(veiculo.getCategoria())
                .valorDiaria(veiculo.getValorDiaria())
                .quilometragem(veiculo.getQuilometragem())
                .disponivel(veiculo.getDisponivel())
                .build();
    }

    public static Veiculo to(VeiculoDto veiculoDto) {
        return Veiculo.builder()
                .id(veiculoDto.getId())
                .marca(veiculoDto.getMarca())
                .modelo(veiculoDto.getModelo())
                .placa(veiculoDto.getPlaca())
                .cor(veiculoDto.getCor())
                .ano(veiculoDto.getAno())
                .chassi(veiculoDto.getChassi())
                .renavam(veiculoDto.getRenavam())
                .combustivel(veiculoDto.getCombustivel())
                .categoria(veiculoDto.getCategoria())
                .valorDiaria(veiculoDto.getValorDiaria())
                .quilometragem(veiculoDto.getQuilometragem())
                .build();
    }
}
