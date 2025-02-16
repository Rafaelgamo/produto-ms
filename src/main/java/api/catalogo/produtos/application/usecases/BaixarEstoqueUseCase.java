package api.catalogo.produtos.application.usecases;

import api.catalogo.produtos.application.gateways.PedidoExternalGateway;
import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.exceptions.ReservaEstoqueExceptions;

import java.util.Map;

public class BaixarEstoqueUseCase {

    private final ProdutoGateway produtoGateway;
    private final PedidoExternalGateway pedidoExternalGateway;


    public BaixarEstoqueUseCase(ProdutoGateway produtoGateway, PedidoExternalGateway pedidoExternalGateway) {
        this.produtoGateway = produtoGateway;
        this.pedidoExternalGateway = pedidoExternalGateway;

    }


    public void reservaEstoque(Long pedidoId, Map<Long, Double> quantidades) {
        var itemIds = quantidades.keySet();
        var produtos = produtoGateway.listarPorIds(itemIds);

        produtos.forEach(produto -> {
            var quantidadeEstoque = produto.getQuantidadeEstoque();
            var quantidadeRequisitada = quantidades.get(produto.getId());
            if (quantidadeEstoque < quantidadeRequisitada) {
                throw new ReservaEstoqueExceptions("quantidade invalida");
            }
            var novaQuantiade = quantidadeEstoque - quantidadeRequisitada;

            produto.setQuantidadeEstoque(novaQuantiade);

            var quantidadeReservada = produto.getQuantidadeReservada();

            produto.setQuantidadeReservada(quantidadeReservada + quantidadeRequisitada);

        });

        produtoGateway.atualizarQuantidades(produtos);
        pedidoExternalGateway.cadastrarPedido(pedidoId, quantidades);

    }



  }
