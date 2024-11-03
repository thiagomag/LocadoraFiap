package br.com.postechfiap.locadoraveiculosfiap.domain.repository;

import br.com.postechfiap.locadoraveiculosfiap.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
