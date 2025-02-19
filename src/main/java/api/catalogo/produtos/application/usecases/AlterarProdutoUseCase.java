package api.catalogo.produtos.application.usecases;

import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.domain.Produto;
import api.catalogo.produtos.infra.dto.AlteraProdutoDTO;

import java.util.NoSuchElementException;
import java.util.Optional;


public class AlterarProdutoUseCase {


    private final ProdutoGateway produtoGateway;

    public AlterarProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public void alterarProduto(Long id, AlteraProdutoDTO data) {
        Optional<Produto> optionalProduct = produtoGateway.buscarPorId(id);
        if (optionalProduct.isEmpty()) {
            throw new NoSuchElementException("Produto não encontrado");
        }

        var produto =  optionalProduct.get();
        produto.setNome(data.nome());
        produto.setTipo(data.tipo());
        produto.setDescricao(data.descricao());
        produto.setValor(data.valor());
        produto.setQuantidadeEstoque(data.quantidadeEstoque());

        produtoGateway.atualizarProduto(id, produto);
    }
}
