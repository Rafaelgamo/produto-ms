package api.catalogo.produtos.infra.persistence;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Table(name = "pedido_external")
@Entity
public class PedidoExternalEntity {

    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="pedido_id")
    private List<PedidoProdutoEntity> pedidos;

    public PedidoExternalEntity(Long id, List<PedidoProdutoEntity> pedidos) {
        this.id = id;
        this.pedidos = pedidos;
    }

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

    public List<PedidoProdutoEntity> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoProdutoEntity> pedidos) {
        this.pedidos = pedidos;
    }
}
