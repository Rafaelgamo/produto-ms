package api.catalogo.produtos.application.usecases;

import api.catalogo.produtos.application.gateways.PedidoExternalGateway;
import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.exceptions.ReservaEstoqueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ReservarEstoqueUseCase {

    private static final Logger log = LoggerFactory.getLogger(ReservarEstoqueUseCase.class);
    private final ProdutoGateway produtoGateway;
    private final PedidoExternalGateway pedidoExternalGateway;


    public ReservarEstoqueUseCase(ProdutoGateway produtoGateway, PedidoExternalGateway pedidoExternalGateway) {
        this.produtoGateway = produtoGateway;
        this.pedidoExternalGateway = pedidoExternalGateway;
    }


    public void reservaEstoque(Long pedidoId, Map<Long, Double> quantidades) {
        var itemIds = quantidades.keySet();
        var produtos = produtoGateway.listarPorIds(itemIds);

        try {
            produtos.forEach(produto -> {
                var quantidadeEstoque = produto.getQuantidadeEstoque();
                var quantidadeRequisitada = quantidades.get(produto.getId());
                if (quantidadeEstoque < quantidadeRequisitada) {
                    throw new ReservaEstoqueException("Quantidade requisitada excede o montante em estoque.");
                }
                var novaQuantidade = quantidadeEstoque - quantidadeRequisitada;

                produto.setQuantidadeEstoque(novaQuantidade);

                var quantidadeReservada = produto.getQuantidadeReservada();

                produto.setQuantidadeReservada(quantidadeReservada + quantidadeRequisitada);

            });
        } catch (ReservaEstoqueException ex) {
            log.debug(ex.getMessage());
            produtoGateway.notificarEstoqueInsuficiente(pedidoId);
            throw ex;
        }

        pedidoExternalGateway.cadastrarPedido(pedidoId, quantidades);
        produtoGateway.salvarReservaEstoque(pedidoId, produtos);
    }

    public void descontarQuantidadeReservadaDosItensDoPedido(Long pedidoId) {
        produtoGateway.descontarQuantidadeReservadaDosItensDoPedido(pedidoId);
    }
}
