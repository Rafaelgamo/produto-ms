package api.catalogo.produtos.config;

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

    @Bean
    public Consumer<Message<NovoPedidoMensagem>> estoqueEntrypoint() {
        return message -> {
            log.info("ESTOQUE CONSUMIU: {}", message.toString());
        };
    }
}
