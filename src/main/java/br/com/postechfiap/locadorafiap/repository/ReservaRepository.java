package br.com.postechfiap.locadorafiap.repository;

import br.com.postechfiap.locadorafiap.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
