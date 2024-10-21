package utils;

import br.com.postechfiap.locadoraveiculosfiap.dto.ReservaDto;
import br.com.postechfiap.locadoraveiculosfiap.entities.Reserva;
import br.com.postechfiap.locadoraveiculosfiap.enuns.CategoriaVeiculosEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservaTestUtil {

    public static Reserva buildReserva() {
        return Reserva.builder()
                .id(1L)
                .dataInicio(LocalDate.now())
                .dataFim(LocalDate.now().plusDays(1L))
                .valorTotal(BigDecimal.TEN)
                .valorDiaria(BigDecimal.TEN)
                .veiculo(VeiculoTestUtil.buildVeiculo())
                .reservaAtiva(true)
                .build();
    }

    public static ReservaDto buildReservaDto() {
        return ReservaDto.builder()
                .id(1L)
                .clienteId(1L)
                .dataInicio(LocalDate.now())
                .dataFim(LocalDate.now().plusDays(1L))
                .valorDiaria(BigDecimal.TEN)
                .categoriaVeiculo(CategoriaVeiculosEnum.ECONOMICO)
                .veiculo(VeiculoTestUtil.buildVeiculoDto())
                .build();
    }
}
