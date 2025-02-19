package api.catalogo.produtos.application.gateways;

import api.catalogo.produtos.domain.Produto;
import api.catalogo.produtos.infra.dto.ProdutoDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface ProdutoGateway {

    Produto cadastrarProduto(Produto produto);

    List<ProdutoDTO> listarTodos();


    void excluirProduto(Long id);

    List<ProdutoDTO> listarPorIds(Collection<Long> id);

    void salvarReservaEstoque(Long pedidoId, List<ProdutoDTO> produtos);

    void descontarQuantidadeReservadaDosItensDoPedido(Long pedidoId);

    Optional<Produto> buscarPorId(Long id);

    void atualizarProduto(Long id, Produto produto);


    void notificarEstoqueInsuficiente(Long pedidoId);
}






