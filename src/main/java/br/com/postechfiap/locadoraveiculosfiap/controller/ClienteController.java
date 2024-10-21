package br.com.postechfiap.locadoraveiculosfiap.controller;

import br.com.postechfiap.locadoraveiculosfiap.dto.ClienteDto;
import br.com.postechfiap.locadoraveiculosfiap.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getTodosClientes() {
        return ResponseEntity.ok(clienteService.getTodosClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.getClienteById(id));
    }

    @PostMapping
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto) {
        return ResponseEntity.ok(clienteService.createCliente(clienteDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id,
                                                    @RequestBody ClienteDto clienteDto) {
        return ResponseEntity.ok(clienteService.updateCliente(id, clienteDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
