package api.catalogo.produtos.domain;

import java.util.Map;

public record Pedido(
        Long pedidoId,
        Map<Long, Double> quantidadesProdutos
) {
}
