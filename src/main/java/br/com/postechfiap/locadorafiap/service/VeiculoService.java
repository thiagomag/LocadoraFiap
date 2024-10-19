package br.com.postechfiap.locadorafiap.service;

import br.com.postechfiap.locadorafiap.dto.VeiculoDto;
import br.com.postechfiap.locadorafiap.exceptions.VeiculoNotFoundException;
import br.com.postechfiap.locadorafiap.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public List<VeiculoDto> getTodosVeiculos() {
        return veiculoRepository.findAll()
                .stream()
                .map(VeiculoDto::from)
                .toList();
    }

    public VeiculoDto getVeiculoById(Long id) {
        return veiculoRepository.findById(id)
                .map(VeiculoDto::from)
                .orElseThrow(() -> new VeiculoNotFoundException(id));
    }

    public VeiculoDto createVeiculo(VeiculoDto veiculoDto) {
        return VeiculoDto.from(veiculoRepository.save(VeiculoDto.to(veiculoDto)));
    }

    public VeiculoDto updateVeiculo(Long id, VeiculoDto veiculoDto) {
        final var veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));

        Optional.ofNullable(veiculoDto.getAno()).ifPresent(veiculo::setAno);
        Optional.ofNullable(veiculoDto.getCor()).ifPresent(veiculo::setCor);
        Optional.ofNullable(veiculoDto.getMarca()).ifPresent(veiculo::setMarca);
        Optional.ofNullable(veiculoDto.getModelo()).ifPresent(veiculo::setModelo);
        Optional.ofNullable(veiculoDto.getPlaca()).ifPresent(veiculo::setPlaca);
        Optional.ofNullable(veiculoDto.getValorDiaria()).ifPresent(veiculo::setValorDiaria);
        Optional.ofNullable(veiculoDto.getCombustivel()).ifPresent(veiculo::setCombustivel);
        Optional.ofNullable(veiculoDto.getCategoria()).ifPresent(veiculo::setCategoria);
        Optional.ofNullable(veiculoDto.getChassi()).ifPresent(veiculo::setChassi);
        Optional.ofNullable(veiculoDto.getRenavam()).ifPresent(veiculo::setRenavam);
        Optional.ofNullable(veiculoDto.getQuilometragem()).ifPresent(veiculo::setQuilometragem);

        return VeiculoDto.from(veiculoRepository.save(veiculo));
    }

    public void deleteVeiculo(Long id) {
        if (veiculoRepository.existsById(id)) {
            veiculoRepository.deleteById(id);
        } else {
            throw new VeiculoNotFoundException(id);
        }
    }
}
