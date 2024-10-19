package br.com.postechfiap.locadorafiap.dto;

import br.com.postechfiap.locadorafiap.entities.Veiculo;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
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
    private String categoria;
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
