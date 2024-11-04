package utils;

import br.com.postechfiap.locadoraveiculosfiap.application.dto.ReservaDto;
import br.com.postechfiap.locadoraveiculosfiap.domain.model.Cliente;
import br.com.postechfiap.locadoraveiculosfiap.domain.model.Reserva;
import br.com.postechfiap.locadoraveiculosfiap.shared.enuns.CategoriaVeiculosEnum;

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
                .cliente(Cliente.builder().id(1L).build())
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
