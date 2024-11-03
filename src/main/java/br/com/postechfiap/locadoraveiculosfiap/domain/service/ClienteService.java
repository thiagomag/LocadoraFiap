package br.com.postechfiap.locadoraveiculosfiap.domain.service;

import br.com.postechfiap.locadoraveiculosfiap.application.dto.ClienteDto;
import br.com.postechfiap.locadoraveiculosfiap.application.handler.exceptions.ClienteNotFoundException;
import br.com.postechfiap.locadoraveiculosfiap.domain.repository.ClienteRepository;
import br.com.postechfiap.locadoraveiculosfiap.domain.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;

    public List<ClienteDto> getTodosClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteDto::from)
                .toList();
    }

    public ClienteDto getClienteById(Long id) {
        return clienteRepository.findById(id)
                .map(ClienteDto::from)
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }


    public ClienteDto createCliente(ClienteDto clienteDto) {
        final var cliente = ClienteDto.to(clienteDto);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return ClienteDto.from(clienteRepository.save(cliente));
    }

    public ClienteDto updateCliente(Long id, ClienteDto clienteDto) {
       final var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));

       Optional.ofNullable(clienteDto.getCnh()).ifPresent(cliente::setCnh);
       Optional.ofNullable(clienteDto.getEmail()).ifPresent(cliente::setEmail);
       Optional.ofNullable(clienteDto.getNome()).ifPresent(cliente::setNome);
       Optional.ofNullable(clienteDto.getCpf()).ifPresent(cliente::setCpf);

       return ClienteDto.from(clienteRepository.save(cliente));
    }

    public void deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new ClienteNotFoundException(id);
        }
    }
}
