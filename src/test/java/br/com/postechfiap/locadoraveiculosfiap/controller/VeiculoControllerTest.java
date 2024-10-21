package br.com.postechfiap.locadoraveiculosfiap.controller;

import br.com.postechfiap.locadoraveiculosfiap.dto.VeiculoDto;
import br.com.postechfiap.locadoraveiculosfiap.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utils.VeiculoTestUtil;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VeiculoControllerTest {

    @InjectMocks
    private VeiculoController veiculoController;

    @Mock
    private VeiculoService veiculoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getTodosVeiculos() {
        // Arrange
        final var veiculos = Collections.singletonList(VeiculoTestUtil.buildVeiculoDto());
        when(veiculoService.getTodosVeiculos()).thenReturn(veiculos);

        // Act
        ResponseEntity<List<VeiculoDto>> response = veiculoController.getTodosVeiculos();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculos, response.getBody());
        verify(veiculoService, times(1)).getTodosVeiculos();
    }

    @Test
    public void getVeiculoById() {
        // Arrange
        Long id = 1L;
        final var veiculo = VeiculoTestUtil.buildVeiculoDto();
        when(veiculoService.getVeiculoById(id)).thenReturn(veiculo);

        // Act
        ResponseEntity<VeiculoDto> response = veiculoController.getVeiculoById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculo, response.getBody());
        verify(veiculoService, times(1)).getVeiculoById(id);
    }

    @Test
    public void createVeiculo() {
        // Arrange
        final var novoVeiculo = VeiculoTestUtil.buildVeiculoDto();
        novoVeiculo.setId(null);
        final var veiculoCriado = VeiculoTestUtil.buildVeiculoDto();
        when(veiculoService.createVeiculo(novoVeiculo)).thenReturn(veiculoCriado);

        // Act
        ResponseEntity<VeiculoDto> response = veiculoController.createVeiculo(novoVeiculo);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculoCriado, response.getBody());
        verify(veiculoService, times(1)).createVeiculo(novoVeiculo);
    }

    @Test
    public void updateVeiculo() {
        // Arrange
        Long id = 1L;
        final var veiculoAtualizado = VeiculoTestUtil.buildVeiculoDto();
        when(veiculoService.updateVeiculo(id, veiculoAtualizado)).thenReturn(veiculoAtualizado);

        // Act
        ResponseEntity<VeiculoDto> response = veiculoController.updateVeiculo(id, veiculoAtualizado);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculoAtualizado, response.getBody());
        verify(veiculoService, times(1)).updateVeiculo(id, veiculoAtualizado);
    }

    @Test
    public void deleteVeiculo() {
        // Arrange
        final var id = 1L;
        doNothing().when(veiculoService).deleteVeiculo(id);

        // Act
        ResponseEntity<Void> response = veiculoController.deleteVeiculo(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(veiculoService, times(1)).deleteVeiculo(id);
    }
}