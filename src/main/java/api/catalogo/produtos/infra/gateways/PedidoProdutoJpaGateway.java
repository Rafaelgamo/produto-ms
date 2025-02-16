package api.catalogo.produtos.infra.gateways;

import api.catalogo.produtos.application.gateways.PedidoProdutoGateway;
import api.catalogo.produtos.infra.persistence.PedidoExternalEntity;
import api.catalogo.produtos.infra.persistence.PedidoProdutoEntity;
import api.catalogo.produtos.infra.persistence.PedidoProdutoRepository;
import api.catalogo.produtos.infra.persistence.ProdutoEntity;

public class PedidoProdutoJpaGateway implements PedidoProdutoGateway {

    private final PedidoProdutoRepository pedidoProdutoRepository;

    public PedidoProdutoJpaGateway(PedidoProdutoRepository pedidoProdutoRepository) {
        this.pedidoProdutoRepository = pedidoProdutoRepository;
    }

    @Override
    public void criarPedidoProduto(Long id, Long produtoId, Double quantidade) {
        var pedido = new PedidoExternalEntity(id);
        var produto = new ProdutoEntity(produtoId);
        var pedidoProduto = new PedidoProdutoEntity(null, pedido, produto, quantidade );

        pedidoProdutoRepository.save(pedidoProduto);
    }
}
