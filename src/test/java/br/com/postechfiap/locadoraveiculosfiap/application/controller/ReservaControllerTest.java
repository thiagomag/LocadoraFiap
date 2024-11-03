package br.com.postechfiap.locadoraveiculosfiap.application.controller;

import br.com.postechfiap.locadoraveiculosfiap.application.dto.ReservaDto;
import br.com.postechfiap.locadoraveiculosfiap.domain.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utils.ReservaTestUtil;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReservaControllerTest {

    @InjectMocks
    private ReservaController reservaController;

    @Mock
    private ReservaService reservaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getTodasReservas() {
        // Arrange
        final var reservas = Collections.singletonList(ReservaTestUtil.buildReservaDto());
        when(reservaService.getTodasReservas()).thenReturn(reservas);

        // Act
        ResponseEntity<List<ReservaDto>> response = reservaController.getTodasReservas();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservas, response.getBody());
        verify(reservaService, times(1)).getTodasReservas();
    }

    @Test
    public void getReservaById() {
        // Arrange
        final var reservaId = 1L;
        final var reserva = ReservaTestUtil.buildReservaDto();
        when(reservaService.getReservaById(reservaId)).thenReturn(reserva);

        // Act
        ResponseEntity<ReservaDto> response = reservaController.getReservaById(reservaId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reserva, response.getBody());
        verify(reservaService, times(1)).getReservaById(reservaId);
    }

    @Test
    public void getReservaByClienteId() {
        // Arrange
        final var clienteId = 1L;
        final var reservas = Collections.singletonList(ReservaTestUtil.buildReservaDto());
        when(reservaService.getReservaByClienteId(clienteId)).thenReturn(reservas);

        // Act
        ResponseEntity<List<ReservaDto>> response = reservaController.getReservaByClienteId(clienteId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservas, response.getBody());
        verify(reservaService, times(1)).getReservaByClienteId(clienteId);
    }

    @Test
    public void getReservaByVeiculoId() {
        // Arrange
        final var veiculoId = 1L;
        final var reservas = Collections.singletonList(ReservaTestUtil.buildReservaDto());
        when(reservaService.getReservaByVeiculoId(veiculoId)).thenReturn(reservas);

        // Act
        ResponseEntity<List<ReservaDto>> response = reservaController.getReservaByVeiculoId(veiculoId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservas, response.getBody());
        verify(reservaService, times(1)).getReservaByVeiculoId(veiculoId);
    }

    @Test
    public void saveReserva() {
        // Arrange
        final var novaReserva = ReservaTestUtil.buildReservaDto();
        novaReserva.setId(null);
        final var reservaCriada = ReservaTestUtil.buildReservaDto();
        when(reservaService.saveReserva(novaReserva)).thenReturn(reservaCriada);

        // Act
        ResponseEntity<ReservaDto> response = reservaController.saveReserva(novaReserva);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservaCriada, response.getBody());
        verify(reservaService, times(1)).saveReserva(novaReserva);
    }

    @Test
    public void retirarVeiculo() {
        // Arrange
        final var reservaId = 1L;
        final var reservaAtualizada = ReservaTestUtil.buildReservaDto();
        when(reservaService.retirarVeiculo(reservaId)).thenReturn(reservaAtualizada);

        // Act
        ResponseEntity<ReservaDto> response = reservaController.retirarVeiculo(reservaId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservaAtualizada, response.getBody());
        verify(reservaService, times(1)).retirarVeiculo(reservaId);
    }

    @Test
    public void devolverVeiculo() {
        // Arrange
        final var reservaId = 1L;
        final var reservaAtualizada = ReservaTestUtil.buildReservaDto();
        when(reservaService.devolverVeiculo(reservaId)).thenReturn(reservaAtualizada);

        // Act
        ResponseEntity<ReservaDto> response = reservaController.devolverVeiculo(reservaId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservaAtualizada, response.getBody());
        verify(reservaService, times(1)).devolverVeiculo(reservaId);
    }

    @Test
    public void updateReserva() {
        // Arrange
        final var reservaId = 1L;
        final var reservaAtualizada = ReservaTestUtil.buildReservaDto();
        when(reservaService.updateReserva(reservaId, reservaAtualizada)).thenReturn(reservaAtualizada);

        // Act
        ResponseEntity<ReservaDto> response = reservaController.updateReserva(reservaId, reservaAtualizada);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservaAtualizada, response.getBody());
        verify(reservaService, times(1)).updateReserva(reservaId, reservaAtualizada);
    }

    @Test
    public void cancelarReserva() {
        // Arrange
        final var reservaId = 1L;
        doNothing().when(reservaService).cancelarReserva(reservaId);

        // Act
        ResponseEntity<String> response = reservaController.cancelarReserva(reservaId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reserva cancelada com id: " + reservaId, response.getBody());
        verify(reservaService, times(1)).cancelarReserva(reservaId);
    }

    @Test
    public void deleteReserva() {
        // Arrange
        final var reservaId = 1L;
        doNothing().when(reservaService).deleteReserva(reservaId);

        // Act
        ResponseEntity<Void> response = reservaController.deleteReserva(reservaId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(reservaService, times(1)).deleteReserva(reservaId);
    }
}
