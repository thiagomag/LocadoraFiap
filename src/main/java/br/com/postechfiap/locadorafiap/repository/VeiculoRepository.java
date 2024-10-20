package br.com.postechfiap.locadorafiap.repository;

import br.com.postechfiap.locadorafiap.entities.Veiculo;
import br.com.postechfiap.locadorafiap.enuns.CategoriaVeiculosEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findVeiculoByCategoria(CategoriaVeiculosEnum categoria);

    List<Veiculo> findVeiculoByCategoriaAndDisponivelIsTrue(CategoriaVeiculosEnum categoria);

}
