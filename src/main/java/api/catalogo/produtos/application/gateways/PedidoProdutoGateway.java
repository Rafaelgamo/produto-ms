package api.catalogo.produtos.application.gateways;


public interface PedidoProdutoGateway {

    void criarPedidoProduto(Long id, Long produtoId, Double quantidade);


}






