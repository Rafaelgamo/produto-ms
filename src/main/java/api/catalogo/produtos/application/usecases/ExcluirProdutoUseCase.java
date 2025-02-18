package api.catalogo.produtos.application.usecases;

import api.catalogo.produtos.application.gateways.ProdutoGateway;

public class ExcluirProdutoUseCase {

    private ProdutoGateway produtoGateway;

    public ExcluirProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public void excluirProduto(Long id) {
        produtoGateway.excluirProduto(id);
    }
}
