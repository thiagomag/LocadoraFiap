package br.com.postechfiap.locadoraveiculosfiap.repository;

import br.com.postechfiap.locadoraveiculosfiap.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
