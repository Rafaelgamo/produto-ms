package api.catalogo.produtos.infra.gateways;

import api.catalogo.produtos.application.gateways.PedidoProdutoGateway;
import api.catalogo.produtos.infra.persistence.PedidoExternalEntity;
import api.catalogo.produtos.infra.persistence.PedidoExternalRepository;
import api.catalogo.produtos.infra.persistence.PedidoProdutoEntity;
import api.catalogo.produtos.infra.persistence.PedidoProdutoRepository;
import api.catalogo.produtos.infra.persistence.ProdutoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class PedidoProdutoJpaGateway implements PedidoProdutoGateway {

    private final ProdutoRepository produtoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;
    private final PedidoExternalRepository pedidoExternalRepository;

    public PedidoProdutoJpaGateway(ProdutoRepository produtoRepository, PedidoProdutoRepository pedidoProdutoRepository, PedidoExternalRepository pedidoExternalRepository) {
        this.produtoRepository = produtoRepository;
        this.pedidoProdutoRepository = pedidoProdutoRepository;
        this.pedidoExternalRepository = pedidoExternalRepository;
    }

    @Override
    @Transactional("transactionManager")
    public void criarPedidoProduto(Long pedidoId, Long produtoId, Double quantidade) {
        var pedido = pedidoExternalRepository.findById(pedidoId);

        if (pedido.isEmpty()) {
            var pedidoSalvo = pedidoExternalRepository.save(new PedidoExternalEntity(pedidoId));
            pedido = Optional.of(pedidoSalvo);
        }

        var produto = produtoRepository.findById(produtoId).orElseThrow();
        var pedidoProduto = new PedidoProdutoEntity(null, pedido.get(), produto, quantidade);

        pedidoProdutoRepository.save(pedidoProduto);
    }
}
