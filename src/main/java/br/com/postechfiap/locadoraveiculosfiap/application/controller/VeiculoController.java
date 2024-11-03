package br.com.postechfiap.locadoraveiculosfiap.application.controller;

import br.com.postechfiap.locadoraveiculosfiap.application.dto.VeiculoDto;
import br.com.postechfiap.locadoraveiculosfiap.domain.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Veiculos", description = "Endpoint para cadastro de veiculos")
@RestController
@RequiredArgsConstructor
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @Operation(summary = "Retorna todos os veículos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículos retornados com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<VeiculoDto>> getTodosVeiculos() {
        return ResponseEntity.ok(veiculoService.getTodosVeiculos());
    }

    @Operation(summary = "Retorna um veículo pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDto> getVeiculoById(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.getVeiculoById(id));
    }

    @Operation(summary = "Cria um veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo criado com sucesso")
    })
    @PostMapping
    public ResponseEntity<VeiculoDto> createVeiculo(@RequestBody VeiculoDto veiculoDto) {
        return ResponseEntity.ok(veiculoService.createVeiculo(veiculoDto));
    }

    @Operation(summary = "Atualiza um veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDto> updateVeiculo(@PathVariable Long id,
                                                    @RequestBody VeiculoDto veiculoDto) {
        return ResponseEntity.ok(veiculoService.updateVeiculo(id, veiculoDto));
    }

    @Operation(summary = "Deleta um veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Veículo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {
        veiculoService.deleteVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}
