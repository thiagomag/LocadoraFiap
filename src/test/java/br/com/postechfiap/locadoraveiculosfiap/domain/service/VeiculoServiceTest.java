package br.com.postechfiap.locadoraveiculosfiap.domain.service;

import br.com.postechfiap.locadoraveiculosfiap.domain.model.Veiculo;
import br.com.postechfiap.locadoraveiculosfiap.domain.repository.VeiculoRepository;
import br.com.postechfiap.locadoraveiculosfiap.shared.enuns.CategoriaVeiculosEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.VeiculoTestUtil;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class VeiculoServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoService veiculoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        veiculoService = new VeiculoService(veiculoRepository);
    }

    @Test
    public void testGetTodosVeiculos() {
        //give
        when(veiculoRepository.findAll())
                .thenReturn(Collections.singletonList(VeiculoTestUtil.buildVeiculo()));
        //when
        final var todosVeiculos = veiculoService.getTodosVeiculos();
        //then
        assertThat(todosVeiculos).isNotEmpty();
    }

    @Test
    public void testGetVeiculoById1() {
        //give
        when(veiculoRepository.findById(anyLong()))
                .thenReturn(Optional.of(VeiculoTestUtil.buildVeiculo()));
        //when
        final var veiculo = veiculoService.getVeiculoById(1L);
        //then
        assertThat(veiculo).isNotNull();
        assertEquals(1L, veiculo.getId());
        assertEquals("Chevrolet", veiculo.getMarca());
        assertEquals("Onix", veiculo.getModelo());
        assertEquals("ABC1234", veiculo.getPlaca());
        assertEquals("Branco", veiculo.getCor());
        assertEquals("2021", veiculo.getAno());
        assertEquals("123456789", veiculo.getChassi());
        assertEquals("987654321", veiculo.getRenavam());
        assertEquals("Flex", veiculo.getCombustivel());
        assertEquals(CategoriaVeiculosEnum.ECONOMICO, veiculo.getCategoria());
        assertEquals("100", veiculo.getValorDiaria());
        assertEquals(1000L, veiculo.getQuilometragem());
        assertEquals(true, veiculo.getDisponivel());
    }

    @Test
    public void testGetVeiculoById2() {
        //give
        when(veiculoRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        //when
        // then
        assertThrows(RuntimeException.class, () -> veiculoService.getVeiculoById(1L));
    }

    @Test
    public void testCreateVeiculo() {

        final var veiculo = VeiculoTestUtil.buildVeiculo();
        when(veiculoRepository.save(veiculo))
                .thenAnswer(invocation -> {
                    final var veiculoSalvo = invocation.getArgument(0, Veiculo.class);
                    assertThat(veiculoSalvo).isNotNull();
                    assertEquals(1L, veiculoSalvo.getId());
                    assertEquals("Chevrolet", veiculoSalvo.getMarca());
                    assertEquals("Onix", veiculoSalvo.getModelo());
                    assertEquals("ABC1234", veiculoSalvo.getPlaca());
                    assertEquals("Branco", veiculoSalvo.getCor());
                    assertEquals("2021", veiculoSalvo.getAno());
                    assertEquals("123456789", veiculoSalvo.getChassi());
                    assertEquals("987654321", veiculoSalvo.getRenavam());
                    assertEquals("Flex", veiculoSalvo.getCombustivel());
                    assertEquals(CategoriaVeiculosEnum.ECONOMICO, veiculoSalvo.getCategoria());
                    assertEquals("100", veiculoSalvo.getValorDiaria());
                    assertEquals(1000L, veiculoSalvo.getQuilometragem());
                    assertEquals(true, veiculoSalvo.getDisponivel());
                    return veiculoSalvo;
                });
    }

    @Test
    public void testUpdateVeiculo1() {
        //given
        final var veiculoDto = VeiculoTestUtil.buildVeiculoDtoToUpdate();
        final var veiculo = VeiculoTestUtil.buildVeiculo();
        when(veiculoRepository.findById(anyLong()))
                .thenReturn(Optional.of(veiculo));
        when(veiculoRepository.save(veiculo))
                .thenAnswer(invocation -> {
                    final var veiculoSalvo = invocation.getArgument(0, Veiculo.class);
                    assertThat(veiculoSalvo).isNotNull();
                    assertEquals("Novo Modelo", veiculoSalvo.getModelo());
                    return veiculoSalvo;
                });

        //when
        final var veiculoAtualizado = veiculoService.updateVeiculo(1L, veiculoDto);

        //then
        assertThat(veiculoAtualizado).isNotNull();
        assertEquals(1L, veiculoAtualizado.getId());
        assertEquals("Chevrolet", veiculoAtualizado.getMarca());
        assertEquals("Novo Modelo", veiculoAtualizado.getModelo());
        assertEquals("ABC1234", veiculoAtualizado.getPlaca());
        assertEquals("Branco", veiculoAtualizado.getCor());
        assertEquals("2021", veiculoAtualizado.getAno());
        assertEquals("123456789", veiculoAtualizado.getChassi());
        assertEquals("987654321", veiculoAtualizado.getRenavam());
        assertEquals("Flex", veiculoAtualizado.getCombustivel());
        assertEquals(CategoriaVeiculosEnum.ECONOMICO, veiculoAtualizado.getCategoria());
        assertEquals("100", veiculoAtualizado.getValorDiaria());
        assertEquals(5000L, veiculoAtualizado.getQuilometragem());
        assertEquals(true, veiculoAtualizado.getDisponivel());
    }

    @Test
    public void testUpdateVeiculo2() {
        //given
        final var veiculoDto = VeiculoTestUtil.buildVeiculoDtoToUpdate();
        when(veiculoRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        //when
        //then
        assertThrows(RuntimeException.class, () -> veiculoService.updateVeiculo(1L, veiculoDto));
    }

    @Test
    public void testDeleteVeiculo() {
        //given
        when(veiculoRepository.existsById(anyLong()))
                .thenReturn(true);
        //when
        veiculoService.deleteVeiculo(1L);
        //then
        //no exception
    }
}
