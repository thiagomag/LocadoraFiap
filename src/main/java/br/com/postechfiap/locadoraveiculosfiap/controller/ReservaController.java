package br.com.postechfiap.locadoraveiculosfiap.controller;

import br.com.postechfiap.locadoraveiculosfiap.dto.ReservaDto;
import br.com.postechfiap.locadoraveiculosfiap.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reserva")
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaDto>> getTodasReservas() {
        return ResponseEntity.ok(reservaService.getTodasReservas());
    }

    @GetMapping("/{reservaId}")
    public ResponseEntity<ReservaDto> getReservaById(@PathVariable Long reservaId) {
        return ResponseEntity.ok(reservaService.getReservaById(reservaId));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ReservaDto>> getReservaByClienteId(@PathVariable Long clienteId) {
        return ResponseEntity.ok(reservaService.getReservaByClienteId(clienteId));
    }

    @GetMapping("/veiculo/{veiculoId}")
    public ResponseEntity<List<ReservaDto>> getReservaByVeiculoId(@PathVariable Long veiculoId) {
        return ResponseEntity.ok(reservaService.getReservaByVeiculoId(veiculoId));
    }

    @PostMapping
    public ResponseEntity<ReservaDto> saveReserva(@RequestBody ReservaDto reservaDto) {
        return ResponseEntity.ok(reservaService.saveReserva(reservaDto));
    }

    @PostMapping("/retirar-veiculo/{reservaId}")
    public ResponseEntity<ReservaDto> retirarVeiculo(@PathVariable Long reservaId) {
        return ResponseEntity.ok(reservaService.retirarVeiculo(reservaId));
    }

    @PostMapping("/devolver-veiculo/{reservaId}")
    public ResponseEntity<ReservaDto> devolverVeiculo(@PathVariable Long reservaId) {
        return ResponseEntity.ok(reservaService.devolverVeiculo(reservaId));
    }


    @PutMapping("/{reservaId}")
    public ResponseEntity<ReservaDto> updateReserva(@PathVariable Long reservaId, @RequestBody ReservaDto reservaDto) {
        return ResponseEntity.ok(reservaService.updateReserva(reservaId, reservaDto));
    }

    @DeleteMapping("/cancelar/{reservaId}")
    public ResponseEntity<String> cancelarReserva(@PathVariable Long reservaId) {
        reservaService.cancelarReserva(reservaId);
        return ResponseEntity.ok(String.format("Reserva cancelada com id: " + reservaId));
    }

    @DeleteMapping("/{reservaId}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long reservaId) {
        reservaService.deleteReserva(reservaId);
        return ResponseEntity.noContent().build();
    }
}
