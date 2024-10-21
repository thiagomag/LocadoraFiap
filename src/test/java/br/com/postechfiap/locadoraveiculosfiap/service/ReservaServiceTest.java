package br.com.postechfiap.locadoraveiculosfiap.service;

import br.com.postechfiap.locadoraveiculosfiap.entities.Reserva;
import br.com.postechfiap.locadoraveiculosfiap.exceptions.ReservaNotFoundException;
import br.com.postechfiap.locadoraveiculosfiap.repository.ClienteRepository;
import br.com.postechfiap.locadoraveiculosfiap.repository.ReservaRepository;
import br.com.postechfiap.locadoraveiculosfiap.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.ClienteTestUtil;
import utils.ReservaTestUtil;
import utils.VeiculoTestUtil;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private ReservaService reservaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        reservaService = new ReservaService(reservaRepository, clienteRepository, veiculoRepository);
    }

    @Test
    public void testGetReservas() {
        //given
        when(reservaRepository.findAll())
                .thenReturn(Collections.singletonList(ReservaTestUtil.buildReserva()));
        //when
        final var reservas = reservaService.getTodasReservas();
        //then
        assertThat(reservas).isNotEmpty();
    }

    @Test
    public void testGetReservaById1() {
        //given
        when(reservaRepository.findById(1L))
                .thenReturn(Optional.of(ReservaTestUtil.buildReserva()));
        //when
        final var reserva = reservaService.getReservaById(1L);
        //then
        assertThat(reserva).isNotNull();
    }

    @Test
    public void testGetReservaById2() {
        //given
        when(reservaRepository.findById(1L))
                .thenReturn(Optional.empty());
        //when
        //then
        assertThrows(ReservaNotFoundException.class, () -> reservaService.getReservaById(1L));
    }

    @Test
    public void testGetReservaByClienteId1() {
        //given
        when(reservaRepository.findReservaByClienteId(1L))
                .thenReturn(Collections.singletonList(ReservaTestUtil.buildReserva()));
        //when
        final var reservas = reservaService.getReservaByClienteId(1L);
        //then
        assertThat(reservas).isNotEmpty();
    }

    @Test
    public void testGetReservaByClienteId2() {
        //given
        when(reservaRepository.findReservaByClienteId(1L))
                .thenReturn(List.of());
        //when
        final var reservas = reservaService.getReservaByClienteId(1L);
        //then
        assertThat(reservas).isEmpty();
    }

    @Test
    public void testGetReservaByVeiculoId1() {
        //given
        when(reservaRepository.findReservaByVeiculoId(1L))
                .thenReturn(Collections.singletonList(ReservaTestUtil.buildReserva()));
        //when
        final var reservas = reservaService.getReservaByVeiculoId(1L);
        //then
        assertThat(reservas).isNotEmpty();
    }

    @Test
    public void testGetReservaByVeiculoId2() {
        //given
        when(reservaRepository.findReservaByVeiculoId(1L))
                .thenReturn(List.of());
        //when
        final var reservas = reservaService.getReservaByVeiculoId(1L);
        //then
        assertThat(reservas).isEmpty();
    }

    @Test
    public void testSaveReserva() {
        //given
        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(ClienteTestUtil.buildCliente()));
        when(veiculoRepository.findVeiculoByCategoriaAndDisponivelIsTrue(any()))
                .thenReturn(List.of(VeiculoTestUtil.buildVeiculo()));
        when(reservaRepository.save(any()))
                .thenAnswer(invocation -> {
                    final var reserva = (Reserva) invocation.getArgument(0);
                    assertEquals(1L, reserva.getId());
                    return reserva;
                });
        //when
        final var reserva = reservaService.saveReserva(ReservaTestUtil.buildReservaDto());
        //then
        assertEquals(1L, reserva.getId());
        assertNotNull(reserva.getVeiculo());
        assertEquals(LocalDate.now(), reserva.getDataInicio());
        assertEquals(LocalDate.now().plusDays(1), reserva.getDataFim());
    }

    @Test
    public void testRetirarVeiculo() {
        // given
        final var reserva = ReservaTestUtil.buildReserva();
        reserva.setVeiculoRetirado(false);
        reserva.setCliente(ClienteTestUtil.buildCliente());
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any(Reserva.class)))
                .thenAnswer(invocation -> {
                    final var reservaAtualizada = (Reserva) invocation.getArgument(0);
                    assertTrue(reservaAtualizada.getVeiculoRetirado());
                    return reservaAtualizada;
                });

        // when
        final var resultado = reservaService.retirarVeiculo(1L);

        // then
        assertTrue(resultado.getReservaRetirada());
        assertTrue(resultado.getReservaAtiva());
    }

    @Test
    public void testDevolverVeiculo() {
        // given
        final var reserva = ReservaTestUtil.buildReserva();
        reserva.setVeiculoRetirado(true);
        reserva.setReservaAtiva(true);
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        // when
        final var resultado = reservaService.devolverVeiculo(1L);

        // then
        assertFalse(resultado.getReservaRetirada());
        assertFalse(resultado.getReservaAtiva());
    }

    @Test
    public void testCancelarReserva1() {
        // given
        final var reserva = ReservaTestUtil.buildReserva();
        reserva.setReservaAtiva(true);
        when(reservaRepository.findById(1L))
                .thenReturn(Optional.empty());

        // when
        // then
        assertThrows(ReservaNotFoundException.class, () -> reservaService.cancelarReserva(1L));
    }

    @Test
    public void testCancelarReserva2() {
        // given
        final var reserva = ReservaTestUtil.buildReserva();
        reserva.setReservaAtiva(true);
        when(reservaRepository.findById(anyLong()))
                .thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any(Reserva.class)))
                .thenAnswer(invocation -> {
                    final var reservaAtualizada = (Reserva) invocation.getArgument(0);
                    assertFalse(reservaAtualizada.getReservaAtiva());
                    return reservaAtualizada;
                });

        // when
        reservaService.cancelarReserva(1L);
        // then
        verify(reservaRepository).save(any(Reserva.class));
        verify(reservaRepository).findById(anyLong());
    }
}
