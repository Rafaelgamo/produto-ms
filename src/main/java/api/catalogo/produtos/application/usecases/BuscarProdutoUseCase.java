package api.catalogo.produtos.application.usecases;

import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.infra.dto.ProdutoDTO;

import java.util.Collection;
import java.util.List;

public class BuscarProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public BuscarProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public List<ProdutoDTO> listarProdutos() {
        return this.produtoGateway.listarTodos();
    }


    public List<ProdutoDTO> listarProdutos(Collection<Long> ids) {
        return this.produtoGateway.listarPorIds(ids);
    }
}

