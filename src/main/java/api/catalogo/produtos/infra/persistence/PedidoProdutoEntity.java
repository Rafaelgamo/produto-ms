package api.catalogo.produtos.infra.persistence;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "pedido_item")
@Entity
public class PedidoProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = PedidoExternalEntity.class, cascade= CascadeType.MERGE)
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private PedidoExternalEntity pedido;

    @ManyToOne(targetEntity = ProdutoEntity.class, cascade= CascadeType.MERGE)
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private ProdutoEntity produto;

    private Double quantidade;

    public PedidoProdutoEntity() {
    }

    public PedidoProdutoEntity(Long id, PedidoExternalEntity pedido, ProdutoEntity produto, Double quantidade) {
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoExternalEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoExternalEntity pedido) {
        this.pedido = pedido;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
}
