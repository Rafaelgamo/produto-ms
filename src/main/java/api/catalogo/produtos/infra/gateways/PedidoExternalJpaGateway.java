package api.catalogo.produtos.infra.gateways;

import api.catalogo.produtos.application.gateways.PedidoExternalGateway;
import api.catalogo.produtos.application.gateways.PedidoProdutoGateway;
import api.catalogo.produtos.infra.persistence.PedidoExternalEntity;
import api.catalogo.produtos.infra.persistence.PedidoExternalRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PedidoExternalJpaGateway implements PedidoExternalGateway {

    private final PedidoExternalRepository pedidoExternalRepository;
    private final PedidoProdutoGateway pedidoProdutoGateway;

    public PedidoExternalJpaGateway(PedidoExternalRepository pedidoExternalRepository, PedidoProdutoGateway pedidoProdutoGateway) {
        this.pedidoExternalRepository = pedidoExternalRepository;
        this.pedidoProdutoGateway = pedidoProdutoGateway;
    }

    @Override
    public PedidoExternalEntity buscarPorId(Long id) {
        return pedidoExternalRepository.findById(id).orElse(null);
    }

    @Override
    public void cadastrarPedido(Long pedidoid, Map<Long, Double> quantidades) {
        var pedido = new PedidoExternalEntity(pedidoid);
        pedidoExternalRepository.save(pedido);

        for (var quantidadeItem : quantidades.entrySet()) {
            var produtoId = quantidadeItem.getKey();
            var quanidade = quantidadeItem.getValue();

            pedidoProdutoGateway.criarPedidoProduto(pedidoid, produtoId, quanidade);
        }
    }
}
