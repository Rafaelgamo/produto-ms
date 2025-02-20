package api.catalogo.produtos.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional("transactionManager")
public interface PedidoProdutoRepository extends JpaRepository<PedidoProdutoEntity, Long> {

    @Query(value = "select pp from PedidoProdutoEntity pp where pp.pedido.id = :pedidoId")
    List<PedidoProdutoEntity> findAllByPedido(Long pedidoId);

    @Query(value = "select pp from PedidoProdutoEntity pp where pp.pedido.id = :pedidoId and pp.produto.id = :produtoId")
    PedidoProdutoEntity findByPedidoAndProduto(Long pedidoId, Long produtoId);
}
