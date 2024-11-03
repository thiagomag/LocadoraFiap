package br.com.postechfiap.locadoraveiculosfiap.domain.service;

import br.com.postechfiap.locadoraveiculosfiap.application.handler.exceptions.ClienteNotFoundException;
import br.com.postechfiap.locadoraveiculosfiap.domain.model.Cliente;
import br.com.postechfiap.locadoraveiculosfiap.domain.repository.ClienteRepository;
import br.com.postechfiap.locadoraveiculosfiap.domain.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.ClienteTestUtil;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteService = new ClienteService(clienteRepository, enderecoRepository);
    }

    @Test
    public void testGetTodosClientes() {
        //give
        when(clienteRepository.findAll())
                .thenReturn(Collections.singletonList(ClienteTestUtil.buildCliente()));

        //when
        final var todosClientes = clienteService.getTodosClientes();

        //then
        assertThat(todosClientes).isNotEmpty();
    }

    @Test
    public void testGetClienteById1() {
        //give
        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(ClienteTestUtil.buildCliente()));

        //when
        final var cliente = clienteService.getClienteById(1L);

        //then
        assertEquals(1L, cliente.getCodCliente());
        assertEquals("12345678901", cliente.getCnh());
        assertEquals("João", cliente.getNome());
        assertEquals("12345678901", cliente.getCpf());
        assertNotNull(cliente.getEnderecos());
        assertNotNull(cliente.getReservas());
    }

    @Test
    public void testGetClienteById2() {
        //give
        final var clienteId = 1L;
        when(clienteRepository.findById(clienteId))
                .thenReturn(Optional.empty());

        //when
        //then
        assertThrows(ClienteNotFoundException.class, () -> clienteService.getClienteById(clienteId));
    }

    @Test
    public void testCreateCliente() {
        //give
        when(clienteRepository.save(any(Cliente.class)))
                .thenAnswer(invocation -> {
                    final var cliente = (Cliente) invocation.getArgument(0);
                    assertEquals(1L, cliente.getId());
                    return cliente;
                });
        when(enderecoRepository.saveAll(any()))
                .thenAnswer(invocation -> {
                    final var enderecos = invocation.getArgument(0);
                    assertThat(enderecos).isNotNull();
                    return enderecos;
                });


        //when
        final var cliente = clienteService.createCliente(ClienteTestUtil.buildClienteDto());

        //then
        assertEquals(1L, cliente.getCodCliente());
        assertEquals("12345678901", cliente.getCnh());
        assertEquals("João", cliente.getNome());
        assertEquals("12345678901", cliente.getCpf());
        assertNotNull(cliente.getEnderecos());
        assertNotNull(cliente.getReservas());
    }

    @Test
    public void testUpdateCliente1() {
        //give
        final var clienteDto = ClienteTestUtil.buildClienteDto();
        clienteDto.setNome("Novo Nome");
        clienteDto.setCnh(null);
        clienteDto.setCpf(null);
        clienteDto.setEmail(null);
        clienteDto.setEnderecos(null);
        clienteDto.setReservas(null);
        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(ClienteTestUtil.buildCliente()));
        when(clienteRepository.save(any()))
                .thenAnswer(invocation -> {
                    final var cliente = (Cliente) invocation.getArgument(0);
                    assertEquals("Novo Nome", cliente.getNome());
                    return cliente;
                });

        //when
        final var cliente = clienteService.updateCliente(1L, clienteDto);

        //then
        assertEquals(1L, cliente.getCodCliente());
        assertEquals("12345678901", cliente.getCnh());
        assertEquals("Novo Nome", cliente.getNome());
        assertEquals("12345678901", cliente.getCpf());
        assertNotNull(cliente.getEnderecos());
        assertNotNull(cliente.getReservas());
    }

    @Test
    public void testUpdateCliente2() {
        //give
        final var clienteId = 1L;
        final var clienteDto = ClienteTestUtil.buildClienteDto();
        when(clienteRepository.findById(clienteId))
                .thenReturn(Optional.empty());

        //when
        //then
        assertThrows(ClienteNotFoundException.class, () -> clienteService.updateCliente(clienteId, clienteDto));
    }

    @Test
    public void testDeleteCliente1() {
        //give
        when(clienteRepository.existsById(1L))
                .thenReturn(false);

        //when
        //then
        assertThrows(ClienteNotFoundException.class, () -> clienteService.getClienteById(1L));
    }
}
