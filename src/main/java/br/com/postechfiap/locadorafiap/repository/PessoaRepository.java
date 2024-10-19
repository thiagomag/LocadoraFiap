package br.com.postechfiap.locadorafiap.repository;

import br.com.postechfiap.locadorafiap.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
