package api.catalogo.produtos.application.usecases;

import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.domain.entity.Produto;

public class CadastrarProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public CadastrarProdutoUseCase(ProdutoGateway produtoGateway){
        this.produtoGateway = produtoGateway;
    }

    public Produto cadastratProduto(Produto produto){
        return produtoGateway.cadastrarProduto(produto);
    }


}
