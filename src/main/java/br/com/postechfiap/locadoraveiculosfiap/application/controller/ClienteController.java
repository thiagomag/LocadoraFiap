package br.com.postechfiap.locadoraveiculosfiap.application.controller;

import br.com.postechfiap.locadoraveiculosfiap.application.dto.ClienteDto;
import br.com.postechfiap.locadoraveiculosfiap.domain.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Endpoint para cadastro de clientes")
@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Retorna todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes retornados com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<ClienteDto>> getTodosClientes() {
        return ResponseEntity.ok(clienteService.getTodosClientes());
    }

    @Operation(summary = "Retorna um cliente pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.getClienteById(id));
    }

    @Operation(summary = "Cria um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente criado com sucesso")
    })
    @PostMapping
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto) {
        return ResponseEntity.ok(clienteService.createCliente(clienteDto));
    }

    @Operation(summary = "Atualiza um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id,
                                                    @RequestBody ClienteDto clienteDto) {
        return ResponseEntity.ok(clienteService.updateCliente(id, clienteDto));
    }

    @Operation(summary = "Deleta um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
