package br.com.postechfiap.locadoraveiculosfiap.repository;

import br.com.postechfiap.locadoraveiculosfiap.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
