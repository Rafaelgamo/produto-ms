package api.catalogo.produtos.infra.gateways.mensageria.dispatcher;

import api.catalogo.produtos.infra.gateways.mensageria.mensagens.NovoPedidoMensagem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class EstoqueReservadoStreamDispatcher implements EstoqueReservadoDispatcher {

    private static final Logger log = LoggerFactory.getLogger(EstoqueReservadoStreamDispatcher.class);

    @Value("${queue.topics.estoque-reservado.name}")
    private String estoqueReservadoTopicName;

    private final StreamBridge streamBridge;

    public EstoqueReservadoStreamDispatcher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Override
    public void enviar(Long pedidoId) {
        log.info("Estoque reservado - enviado para '{}':  {}", estoqueReservadoTopicName, pedidoId);
        streamBridge.send(estoqueReservadoTopicName, pedidoId);
    }

}
