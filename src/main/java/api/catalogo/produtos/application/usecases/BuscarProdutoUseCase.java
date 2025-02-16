package api.catalogo.produtos.application.usecases;

import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.domain.entity.Produto;
import api.catalogo.produtos.infra.dto.ListaProdutoDTO;
import api.catalogo.produtos.infra.persistence.ProdutoEntity;

import java.util.List;

public class BuscarProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public BuscarProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public List<Produto> listarProdutos() {
        return this.produtoGateway.listarTodos();
    }




}

