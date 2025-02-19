package api.catalogo.produtos.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProdutoEntity, Long> {

    @Query(value = "select pp from PedidoProdutoEntity pp where pp.pedido.id = ?")
    List<PedidoProdutoEntity> findAllByPedido(Long pedidoId);
}
