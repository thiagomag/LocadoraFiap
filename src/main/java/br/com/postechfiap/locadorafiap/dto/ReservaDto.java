package br.com.postechfiap.locadorafiap.dto;

import br.com.postechfiap.locadorafiap.entities.Reserva;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDto {

    private Long id;
    private String dataInicio;
    private String dataFim;
    private BigDecimal valorTotal;
    private VeiculoDto veiculo;

    public static ReservaDto from(Reserva reserva) {
        return ReservaDto.builder()
                .id(reserva.getId())
                .dataInicio(reserva.getDataInicio())
                .dataFim(reserva.getDataFim())
                .valorTotal(reserva.getValorTotal())
                .veiculo(VeiculoDto.from(reserva.getVeiculo()))
                .build();
    }

    public static List<ReservaDto> from(List<Reserva> reservas) {
        if (reservas == null) {
            return null;
        }
        return reservas.stream().map(ReservaDto::from).toList();
    }

    public static Reserva to(ReservaDto reservaDto) {
        return Reserva.builder()
                .id(reservaDto.getId())
                .dataInicio(reservaDto.getDataInicio())
                .dataFim(reservaDto.getDataFim())
                .valorTotal(reservaDto.getValorTotal())
                .veiculo(VeiculoDto.to(reservaDto.getVeiculo()))
                .build();
    }

    public static List<Reserva> to(List<ReservaDto> reservaDto) {
        if (reservaDto == null) {
            return null;
        }
        return reservaDto.stream().map(ReservaDto::to).toList();
    }
}
