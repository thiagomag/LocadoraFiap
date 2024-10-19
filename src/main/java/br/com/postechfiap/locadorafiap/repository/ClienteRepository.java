package br.com.postechfiap.locadorafiap.repository;

import br.com.postechfiap.locadorafiap.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
