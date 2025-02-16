package api.catalogo.produtos.infra.gateways.mensageria.mensagens;

import java.util.Map;

public record NovoPedidoMensagem(
        Long pedidoId,
        Map<Long, Double> itens
) {
}
