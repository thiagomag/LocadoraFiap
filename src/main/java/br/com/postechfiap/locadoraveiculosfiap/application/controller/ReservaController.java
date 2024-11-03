package br.com.postechfiap.locadoraveiculosfiap.application.controller;

import br.com.postechfiap.locadoraveiculosfiap.application.dto.ReservaDto;
import br.com.postechfiap.locadoraveiculosfiap.domain.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Reservas", description = "Endpoint para cadastro e manipulação de reservas")
@RestController
@RequiredArgsConstructor
@RequestMapping("/reserva")
public class ReservaController {

    private final ReservaService reservaService;

    @Operation(summary = "Retorna todas as reservas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas retornadas com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<ReservaDto>> getTodasReservas() {
        return ResponseEntity.ok(reservaService.getTodasReservas());
    }

    @Operation(summary = "Retorna uma reserva pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada")
    })
    @GetMapping("/{reservaId}")
    public ResponseEntity<ReservaDto> getReservaById(@PathVariable Long reservaId) {
        return ResponseEntity.ok(reservaService.getReservaById(reservaId));
    }

    @Operation(summary = "Retorna uma reserva pelo id do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada")
    })
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ReservaDto>> getReservaByClienteId(@PathVariable Long clienteId) {
        return ResponseEntity.ok(reservaService.getReservaByClienteId(clienteId));
    }

    @Operation(summary = "Retorna uma reserva pelo id do veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada")
    })
    @GetMapping("/veiculo/{veiculoId}")
    public ResponseEntity<List<ReservaDto>> getReservaByVeiculoId(@PathVariable Long veiculoId) {
        return ResponseEntity.ok(reservaService.getReservaByVeiculoId(veiculoId));
    }

    @Operation(summary = "Cria uma reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva criada com sucesso")
    })
    @PostMapping
    public ResponseEntity<ReservaDto> saveReserva(@RequestBody ReservaDto reservaDto) {
        return ResponseEntity.ok(reservaService.saveReserva(reservaDto));
    }

    @Operation(summary = "Retira um veículo da reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo retirado com sucesso")
    })
    @PostMapping("/retirar-veiculo/{reservaId}")
    public ResponseEntity<ReservaDto> retirarVeiculo(@PathVariable Long reservaId) {
        return ResponseEntity.ok(reservaService.retirarVeiculo(reservaId));
    }

    @Operation(summary = "Devolve um veículo da reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo devolvido com sucesso")
    })
    @PostMapping("/devolver-veiculo/{reservaId}")
    public ResponseEntity<ReservaDto> devolverVeiculo(@PathVariable Long reservaId) {
        return ResponseEntity.ok(reservaService.devolverVeiculo(reservaId));
    }

    @Operation(summary = "Atualiza uma reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada")
    })
    @PutMapping("/{reservaId}")
    public ResponseEntity<ReservaDto> updateReserva(@PathVariable Long reservaId, @RequestBody ReservaDto reservaDto) {
        return ResponseEntity.ok(reservaService.updateReserva(reservaId, reservaDto));
    }

    @Operation(summary = "Cancela uma reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva cancelada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada")
    })
    @DeleteMapping("/cancelar/{reservaId}")
    public ResponseEntity<String> cancelarReserva(@PathVariable Long reservaId) {
        reservaService.cancelarReserva(reservaId);
        return ResponseEntity.ok(String.format("Reserva cancelada com id: " + reservaId));
    }

    @Operation(summary = "Deleta uma reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reserva deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada")
    })
    @DeleteMapping("/{reservaId}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long reservaId) {
        reservaService.deleteReserva(reservaId);
        return ResponseEntity.noContent().build();
    }
}
