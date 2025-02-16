package api.catalogo.produtos.infra.persistence;

import api.catalogo.produtos.infra.dto.ListaProdutoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    Optional<ProdutoEntity> findById(Long id);

}
