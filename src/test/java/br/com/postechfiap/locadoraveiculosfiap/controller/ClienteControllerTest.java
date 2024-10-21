package br.com.postechfiap.locadoraveiculosfiap.controller;

import br.com.postechfiap.locadoraveiculosfiap.dto.ClienteDto;
import br.com.postechfiap.locadoraveiculosfiap.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utils.ClienteTestUtil;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getTodosClientes() {
        // Arrange
        final var clientes = Collections.singletonList(
                ClienteTestUtil.buildClienteDto());
        when(clienteService.getTodosClientes()).thenReturn(clientes);

        // Act
        ResponseEntity<List<ClienteDto>> response = clienteController.getTodosClientes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientes, response.getBody());
        verify(clienteService, times(1)).getTodosClientes();
    }

    @Test
    public void getClienteById() {
        // Arrange
        Long id = 1L;
        final var cliente = ClienteTestUtil.buildClienteDto();
        when(clienteService.getClienteById(id)).thenReturn(cliente);

        // Act
        ResponseEntity<ClienteDto> response = clienteController.getClienteById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
        verify(clienteService, times(1)).getClienteById(id);
    }

    @Test
    public void createCliente() {
        // Arrange
        final var novoCliente = ClienteTestUtil.buildClienteDto();
        novoCliente.setCodCliente(null);
        final var clienteCriado = ClienteTestUtil.buildClienteDto();
        when(clienteService.createCliente(novoCliente)).thenReturn(clienteCriado);

        // Act
        ResponseEntity<ClienteDto> response = clienteController.createCliente(novoCliente);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteCriado, response.getBody());
        verify(clienteService, times(1)).createCliente(novoCliente);
    }

    @Test
    public void updateCliente() {
        // Arrange
        Long id = 1L;
        final var clienteAtualizado = ClienteTestUtil.buildClienteDto();
        when(clienteService.updateCliente(id, clienteAtualizado)).thenReturn(clienteAtualizado);

        // Act
        ResponseEntity<ClienteDto> response = clienteController.updateCliente(id, clienteAtualizado);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteAtualizado, response.getBody());
        verify(clienteService, times(1)).updateCliente(id, clienteAtualizado);
    }

    @Test
    public void deleteCliente() {
        // Arrange
        Long id = 1L;
        doNothing().when(clienteService).deleteCliente(id);

        // Act
        ResponseEntity<Void> response = clienteController.deleteCliente(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(clienteService, times(1)).deleteCliente(id);
    }
}
