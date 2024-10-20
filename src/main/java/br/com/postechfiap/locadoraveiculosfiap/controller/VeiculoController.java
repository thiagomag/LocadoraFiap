package br.com.postechfiap.locadoraveiculosfiap.controller;

import br.com.postechfiap.locadoraveiculosfiap.dto.VeiculoDto;
import br.com.postechfiap.locadoraveiculosfiap.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<VeiculoDto>> getTodosVeiculos() {
        return ResponseEntity.ok(veiculoService.getTodosVeiculos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDto> getVeiculoById(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.getVeiculoById(id));
    }

    @PostMapping
    public ResponseEntity<VeiculoDto> createVeiculo(@RequestBody VeiculoDto veiculoDto) {
        return ResponseEntity.ok(veiculoService.createVeiculo(veiculoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDto> updateVeiculo(@PathVariable Long id,
                                                    @RequestBody VeiculoDto veiculoDto) {
        return ResponseEntity.ok(veiculoService.updateVeiculo(id, veiculoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {
        veiculoService.deleteVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}
