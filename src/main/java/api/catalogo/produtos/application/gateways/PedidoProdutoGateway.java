package api.catalogo.produtos.application.gateways;

import api.catalogo.produtos.domain.entity.Produto;
import api.catalogo.produtos.infra.dto.ProdutoDTO;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface PedidoProdutoGateway {

    void criarPedidoProduto(Long id, Long produtoId, Double quantidade);


}






