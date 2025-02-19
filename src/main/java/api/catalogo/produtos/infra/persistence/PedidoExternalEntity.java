package api.catalogo.produtos.infra.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "pedido_external")
@Entity
public class PedidoExternalEntity {

    @Id
    private Long id;

    public PedidoExternalEntity() {
    }

    public PedidoExternalEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
