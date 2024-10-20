package br.com.postechfiap.locadorafiap.service;

import br.com.postechfiap.locadorafiap.dto.ReservaDto;
import br.com.postechfiap.locadorafiap.entities.Reserva;
import br.com.postechfiap.locadorafiap.entities.Veiculo;
import br.com.postechfiap.locadorafiap.exceptions.ClienteNotFoundException;
import br.com.postechfiap.locadorafiap.exceptions.ReservaNotFoundException;
import br.com.postechfiap.locadorafiap.exceptions.VeiculoNotFoundException;
import br.com.postechfiap.locadorafiap.repository.ClienteRepository;
import br.com.postechfiap.locadorafiap.repository.ReservaRepository;
import br.com.postechfiap.locadorafiap.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;

    public List<ReservaDto> getTodasReservas() {
        return reservaRepository.findAll()
                .stream()
                .map(ReservaDto::from)
                .toList();
    }

    public ReservaDto getReservaById(Long id) {
        return reservaRepository.findById(id)
                .map(ReservaDto::from)
                .orElseThrow(() -> new ReservaNotFoundException(id));
    }

    public List<ReservaDto> getReservaByClienteId(Long clienteId) {
        return reservaRepository.findReservaByClienteId(clienteId)
                .stream()
                .map(ReservaDto::from)
                .collect(Collectors.toList());
    }

    public List<ReservaDto> getReservaByVeiculoId(Long veiculoId) {
        return reservaRepository.findReservaByVeiculoId(veiculoId)
                .stream()
                .map(ReservaDto::from)
                .collect(Collectors.toList());
    }

    public ReservaDto saveReserva(ReservaDto reservaDto) {
        final var veiculo = veiculoRepository.findVeiculoByCategoriaAndDisponivelIsTrue(reservaDto.getCategoriaVeiculo())
                .stream().findFirst()
                .orElseThrow(() -> new VeiculoNotFoundException("Veiculo não encontrado na categoria: " + reservaDto.getCategoriaVeiculo().getDescricao()));
        final var reserva = ReservaDto.to(reservaDto);
        reserva.setVeiculo(veiculo);
        return ReservaDto.from(reservaRepository.save(reserva));
    }

    public ReservaDto updateReserva(Long reservaId, ReservaDto reservaDto) {
        final var reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new ReservaNotFoundException(reservaId));

        Optional.ofNullable(reservaDto.getClienteId())
                .map(clienteId -> clienteRepository.findById(clienteId)
                        .map(cliente -> {
                            reserva.setCliente(cliente);
                            return cliente;
                        }).orElseThrow( () -> new ClienteNotFoundException(clienteId)));

        Optional.ofNullable(reservaDto.getCategoriaVeiculo()).map(categoriaVeiculosEnum -> veiculoRepository.findVeiculoByCategoriaAndDisponivelIsTrue(categoriaVeiculosEnum)
                .stream().findFirst()
                .map(veiculo -> {
                    reserva.setVeiculo(veiculo);
                    return veiculo;
                }).orElseThrow( () -> new VeiculoNotFoundException("Veiculo não encontrado na categoria: " + categoriaVeiculosEnum.getDescricao())));

        Optional.ofNullable(reservaDto.getDataInicio()).ifPresent(reserva::setDataInicio);
        Optional.ofNullable(reservaDto.getDataFim()).ifPresent(reserva::setDataFim);
        Optional.ofNullable(reservaDto.getValorDiaria()).ifPresent(reserva::setValorDiaria);

        return ReservaDto.from(reservaRepository.save(ReservaDto.to(reservaDto)));
    }

    public void deleteReserva(Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
        } else {
            throw new ReservaNotFoundException(id);
        }
    }

    public ReservaDto retirarVeiculo(Long reservaId) {
        return reservaRepository.findById(reservaId)
                .map(reserva -> {
                    final var reservaVerificada = verificarReserva(reserva);
                    reservaVerificada.setVeiculoRetirado(true);
                    atualizarVeiculo(reservaVerificada.getVeiculo(), true);
                    return ReservaDto.from(reservaRepository.save(reservaVerificada));
                }).orElseThrow(() -> new ReservaNotFoundException(reservaId));
    }

    private Reserva verificarReserva(Reserva reserva) {
        final var veiculo = reserva.getVeiculo();
        if (!reserva.getReservaAtiva()) {
            throw new RuntimeException("Reserva não está ativa");
        }
        if (reserva.getVeiculoRetirado()) {
            throw new RuntimeException("Reserva já retirado");
        }
        if (!veiculo.getDisponivel()) {
            final var veiculoAtualizado = veiculoRepository.findVeiculoByCategoriaAndDisponivelIsTrue(reserva.getVeiculo().getCategoria())
                    .stream().findFirst()
                    .orElseThrow(() -> new VeiculoNotFoundException("Veiculo não encontrado na categoria: " + reserva.getVeiculo().getCategoria().getDescricao()));
            reserva.setVeiculo(veiculoAtualizado);
        }
        if (reserva.getDataInicio().isAfter(LocalDate.now())) {
            throw new RuntimeException("Reserva ainda não está disponível para retirada");
        }
        return reserva;
    }

    public ReservaDto devolverVeiculo(Long reservaId) {
        return reservaRepository.findById(reservaId)
                .map(reserva -> {
                    final var reservaDevolucao = verificarDevolucao(reserva);
                    reservaDevolucao.setVeiculoRetirado(false);
                    reservaDevolucao.setReservaAtiva(false);
                    atualizarVeiculo(reservaDevolucao.getVeiculo(), false);
                    return ReservaDto.from(reservaRepository.save(reservaDevolucao));
                }).orElseThrow(() -> new ReservaNotFoundException(reservaId));
    }

    private Reserva verificarDevolucao(Reserva reserva) {
        if (!reserva.getVeiculoRetirado()) {
            throw new RuntimeException("Veiculo não retirado");
        }
        if (!reserva.getReservaAtiva()) {
            throw new RuntimeException("Reserva não está ativa");
        }
        if (reserva.getDataFim().isBefore(LocalDate.now())) {
            atualizarValorTotal(reserva);
        }
        return reserva;
    }

    private void atualizarValorTotal(Reserva reserva) {
        final var dataAtual = LocalDate.now();
        final var diasAtraso = dataAtual.compareTo(reserva.getDataFim());
        final var valorAdicional = reserva.getValorDiaria().multiply(BigDecimal.valueOf(diasAtraso));
        final var valorTotal = reserva.getValorDiaria().add(valorAdicional);
        reserva.setValorTotal(valorTotal);
    }

    private void atualizarVeiculo(Veiculo veiculo, boolean retirada) {
        veiculo.setDisponivel(!retirada);
        veiculoRepository.save(veiculo);
    }

    public void cancelarReserva(Long reservaId) {
        reservaRepository.findById(reservaId)
                .map(reserva -> {
                    reserva.setReservaAtiva(false);
                    return reservaRepository.save(reserva);
                }).orElseThrow(() -> new ReservaNotFoundException(reservaId));
    }
}
