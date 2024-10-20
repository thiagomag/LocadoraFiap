package br.com.postechfiap.locadorafiap.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private BigDecimal valorDiaria;
    private BigDecimal valorTotal;
    private Boolean reservaAtiva;
    private Boolean veiculoRetirado;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
