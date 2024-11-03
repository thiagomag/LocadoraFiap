package br.com.postechfiap.locadoraveiculosfiap.domain.repository;

import br.com.postechfiap.locadoraveiculosfiap.domain.model.Veiculo;
import br.com.postechfiap.locadoraveiculosfiap.shared.enuns.CategoriaVeiculosEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findVeiculoByCategoria(CategoriaVeiculosEnum categoria);

    List<Veiculo> findVeiculoByCategoriaAndDisponivelIsTrue(CategoriaVeiculosEnum categoria);

}
