package br.com.postechfiap.locadoraveiculosfiap.repository;

import br.com.postechfiap.locadoraveiculosfiap.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findReservaByClienteId(Long id);

    List<Reserva> findReservaByVeiculoId(Long id);
}
