package api.catalogo.produtos.DomainProduto.TesteUseCase;


import api.catalogo.produtos.application.gateways.PedidoExternalGateway;
import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.application.usecases.ReservarEstoqueUseCase;
import api.catalogo.produtos.domain.Produto;
import api.catalogo.produtos.infra.persistence.ProdutoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;

public class ReservarProdutoEstoqueUseCaseTest {

    private ProdutoGateway produtoGateway;
    private PedidoExternalGateway pedidoExternalGateway;
    private ReservarEstoqueUseCase reservarEstoqueUseCase;


    @BeforeEach
    void setUp() {
        produtoGateway = mock(ProdutoGateway.class);
        pedidoExternalGateway = mock(PedidoExternalGateway.class);
        reservarEstoqueUseCase = new ReservarEstoqueUseCase(produtoGateway, pedidoExternalGateway);
    }

    @Test
    void reservaEstoque() {
        produtoGateway.salvarReservaEstoque(1L, List.of());
    }

    @Test
    void descontarQuantidadeReservadaDosItensDoPedido(){

    }

}
