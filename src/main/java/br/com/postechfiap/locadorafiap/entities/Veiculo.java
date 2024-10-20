package br.com.postechfiap.locadorafiap.entities;

import br.com.postechfiap.locadorafiap.enuns.CategoriaVeiculosEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private String ano;
    private String chassi;
    private String renavam;
    private String combustivel;
    private CategoriaVeiculosEnum categoria;
    private String valorDiaria;
    private Long quilometragem;
    @Builder.Default
    private Boolean disponivel = true;

    @OneToMany(mappedBy = "veiculo")
    private List<Reserva> reservas;
}
