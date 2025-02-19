package api.catalogo.produtos.config;

import api.catalogo.produtos.application.usecases.ReservarEstoqueUseCase;
import api.catalogo.produtos.infra.gateways.mensageria.mensagens.EntregaConcluidaMensagem;
import api.catalogo.produtos.infra.gateways.mensageria.mensagens.NovoPedidoMensagem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class ConsumerConfig {

    private static final Logger log = LoggerFactory.getLogger(ConsumerConfig.class);

    private final ReservarEstoqueUseCase reservarEstoqueUseCase;

    public ConsumerConfig(ReservarEstoqueUseCase reservarEstoqueUseCase) {
        this.reservarEstoqueUseCase = reservarEstoqueUseCase;
    }

    @Bean
    public Consumer<Message<NovoPedidoMensagem>> novoPedidoEntrypoint() {
        return message -> {
            log.info("NOVO PEDIDO RECEBIDO: {}", message.toString());

            var novoPedido = message.getPayload();
            reservarEstoqueUseCase.reservaEstoque(novoPedido.pedidoId(), novoPedido.itens());
        };
    }

    @Bean
    public Consumer<Message<EntregaConcluidaMensagem>> entregaConcluidaEntrypoint() {
        return message -> {
            log.info("ENTREGA CONCLUIDA: pedidoId={}", message.toString());

            var pedidoId = message.getPayload().pedidoId();
            reservarEstoqueUseCase.descontarQuantidadeReservadaDosItensDoPedido(pedidoId);
        };
    }
}
