package br.com.postechfiap.locadoraveiculosfiap.application.dto;

import br.com.postechfiap.locadoraveiculosfiap.domain.model.Reserva;
import br.com.postechfiap.locadoraveiculosfiap.shared.enuns.CategoriaVeiculosEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Reserva", description = "Representa uma reserva", example = """
        {
          "id": 123,
          "dataInicio": "2021-12-01",
          "dataFim": "2021-12-10",
          "valorDiaria": 100.00,
          "categoriaVeiculo": "SEDAN",
          "clienteId": 123,
          "reservaAtiva": true,
          "reservaRetirada": false
        }
        """)
public class ReservaDto {

    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private BigDecimal valorDiaria;
    private CategoriaVeiculosEnum categoriaVeiculo;
    private Long clienteId;
    @Builder.Default
    private Boolean reservaAtiva = false;
    @Builder.Default
    private Boolean reservaRetirada = false;
    private VeiculoDto veiculo;

    public static ReservaDto from(Reserva reserva) {
        return ReservaDto.builder()
                .id(reserva.getId())
                .dataInicio(reserva.getDataInicio())
                .dataFim(reserva.getDataFim())
                .veiculo(VeiculoDto.from(reserva.getVeiculo()))
                .valorDiaria(reserva.getValorDiaria())
                .categoriaVeiculo(reserva.getVeiculo().getCategoria())
                .clienteId(reserva.getCliente().getId())
                .reservaAtiva(reserva.getReservaAtiva())
                .reservaRetirada(reserva.getVeiculoRetirado())
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
                .valorTotal(buildValorTotal(reservaDto))
                .veiculo(VeiculoDto.to(reservaDto.getVeiculo()))
                .valorDiaria(reservaDto.getValorDiaria())
                .reservaAtiva(reservaDto.getReservaAtiva())
                .veiculoRetirado(reservaDto.getReservaRetirada())
                .build();
    }

    private static BigDecimal buildValorTotal(ReservaDto reservaDto) {
        final var dias = reservaDto.getDataFim().getDayOfYear() - reservaDto.getDataInicio().getDayOfYear();
        return reservaDto.getValorDiaria().multiply(BigDecimal.valueOf(dias));
    }

    public static List<Reserva> to(List<ReservaDto> reservaDto) {
        if (reservaDto == null) {
            return null;
        }
        return reservaDto.stream().map(ReservaDto::to).toList();
    }
}
