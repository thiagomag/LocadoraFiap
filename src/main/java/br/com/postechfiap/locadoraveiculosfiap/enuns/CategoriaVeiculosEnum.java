package br.com.postechfiap.locadoraveiculosfiap.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoriaVeiculosEnum {

    ECONOMICO("Econômico", "Carros pequenos e acessíveis com baixo consumo de combustível."),
    COMPACTO("Compacto", "Carros ligeiramente maiores e mais confortáveis que os econômicos."),
    INTERMEDIARIO("Intermediário", "Carros de médio porte, confortáveis e espaçosos."),
    FULL_SIZE("Full-Size", "Carros grandes e confortáveis, com mais espaço para passageiros e bagagem."),
    SUV("SUV", "Veículos utilitários esportivos, ideais para terrenos mais difíceis e famílias."),
    LUXO("Luxo", "Carros de alta performance e acabamento sofisticado, voltados para clientes exigentes."),
    MINIVAN("Minivan", "Veículos espaçosos, ideais para grandes famílias ou grupos."),
    PICKUP("Pickup", "Caminhonetes com caçamba, usadas para transporte de carga ou terrenos rurais."),
    ESPORTIVO("Esportivo", "Carros de alta performance e design voltado para velocidade e estilo."),
    ELETRICO_HIBRIDO("Elétrico/Híbrido", "Veículos ecológicos que utilizam eletricidade ou uma combinação de eletricidade e combustível."),
    CAMINHAO("Caminhão", "Veículos projetados para transporte de mercadorias.");

    private final String descricao;
    private final String detalhes;

}
