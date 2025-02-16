package api.catalogo.produtos.application.gateways;

import api.catalogo.produtos.domain.entity.Produto;
import api.catalogo.produtos.infra.dto.ProdutoDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface ProdutoGateway {

    Produto cadastrarProduto(Produto produto);

    List<Produto> listarTodos();


    void excluirProduto(Long id);

    List<ProdutoDTO> listarPorIds(Collection<Long> id);

    void atualizarQuantidades(List<ProdutoDTO> produtos);



    Optional<Produto> buscarPorId(Long id);

    void atualizarProduto(Long id, Produto produto);


}






