package api.catalogo.produtos.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoExternalRepository extends JpaRepository<PedidoExternalEntity, Long> {

}
