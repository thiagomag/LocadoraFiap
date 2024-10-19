package br.com.postechfiap.locadorafiap.repository;

import br.com.postechfiap.locadorafiap.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
