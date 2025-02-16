package api.catalogo.produtos.infra.controller;

import java.util.Map;

public record teste(
        Long pedidoId,
        Map<Long, Double> quantidades

) {
}
