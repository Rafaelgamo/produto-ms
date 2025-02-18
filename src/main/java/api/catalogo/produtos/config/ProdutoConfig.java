package api.catalogo.produtos.config;

import api.catalogo.produtos.application.gateways.PedidoExternalGateway;
import api.catalogo.produtos.application.gateways.PedidoProdutoGateway;
import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.application.usecases.*;
import api.catalogo.produtos.infra.gateways.PedidoProdutoJpaGateway;
import api.catalogo.produtos.infra.gateways.ProdutoEntityMapper;
import api.catalogo.produtos.infra.gateways.ProdutoJpaGateway;
import api.catalogo.produtos.infra.gateways.mensageria.dispatcher.EstoqueInsuficienteDispatcher;
import api.catalogo.produtos.infra.gateways.mensageria.dispatcher.EstoqueInsuficienteStreamDispatcher;
import api.catalogo.produtos.infra.gateways.mensageria.dispatcher.EstoqueReservadoStreamDispatcher;
import api.catalogo.produtos.infra.persistence.PedidoProdutoRepository;
import api.catalogo.produtos.infra.persistence.ProdutoRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfig {

    private final StreamBridge streamBridge;

    public ProdutoConfig(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Bean
    CadastrarProdutoUseCase cadastrarProdutoUsesCase(ProdutoGateway produtoGateway) {
        return new CadastrarProdutoUseCase(produtoGateway);
    }

    @Bean
    BuscarProdutoUseCase buscarProdutoUseCase(ProdutoGateway produtoGateway) {
        return new BuscarProdutoUseCase(produtoGateway);
    }

    @Bean
    ReservarEstoqueUseCase reservarEstoqueUseCase(ProdutoGateway produtoGateway, PedidoExternalGateway pedidoExternalGateway){
        return new ReservarEstoqueUseCase(produtoGateway, pedidoExternalGateway);
    }

    @Bean
    AlterarProdutoUseCase alterarProdutoUseCase(ProdutoGateway produtoGateway) {
        return new AlterarProdutoUseCase(produtoGateway);
    }

    @Bean
    ExcluirProdutoUseCase excluirProdutoUseCase(ProdutoGateway produtoGateway) {
        return new ExcluirProdutoUseCase(produtoGateway);
    }


    @Bean
    PedidoProdutoGateway pedidoProdutoGateway(PedidoProdutoRepository pedidoProdutoRepository) {
        return new PedidoProdutoJpaGateway(pedidoProdutoRepository);
    }

    @Bean
    ProdutoGateway produtoGateway(ProdutoRepository produtoRepository, EstoqueReservadoStreamDispatcher estoqueReservadoStreamDispatcher, EstoqueInsuficienteDispatcher estoqueInsuficienteDispatcher, ProdutoEntityMapper produtoEntityMapper){
        return new ProdutoJpaGateway(produtoRepository, estoqueReservadoStreamDispatcher, estoqueInsuficienteDispatcher, produtoEntityMapper);
    }

    @Bean
    ProdutoEntityMapper produtoEntityMapper() {
        return new ProdutoEntityMapper();
    }

    @Bean
    EstoqueInsuficienteDispatcher estoqueInsuficienteDispatcher() {
        return new EstoqueInsuficienteStreamDispatcher(streamBridge);
    }

    @Bean
    EstoqueReservadoStreamDispatcher estoqueReservadoStreamDispatcher() {
        return new EstoqueReservadoStreamDispatcher(streamBridge);
    }

}

