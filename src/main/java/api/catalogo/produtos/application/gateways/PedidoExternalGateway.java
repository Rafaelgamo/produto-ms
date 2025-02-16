package api.catalogo.produtos.application.gateways;

import api.catalogo.produtos.infra.persistence.PedidoExternalEntity;

import java.util.Map;

public interface PedidoExternalGateway {

    PedidoExternalEntity buscarPorId(Long id);

    void cadastrarPedido(Long id, Map<Long, Double> quantidade);
}
