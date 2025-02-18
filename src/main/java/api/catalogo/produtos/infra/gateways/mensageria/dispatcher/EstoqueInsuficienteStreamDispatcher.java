package api.catalogo.produtos.infra.gateways.mensageria.dispatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class EstoqueInsuficienteStreamDispatcher implements EstoqueInsuficienteDispatcher {

    private static final Logger log = LoggerFactory.getLogger(EstoqueInsuficienteStreamDispatcher.class);
    @Value("${queue.topics.estoque-insuficiente.name}")
    private String estoqueInsuficienteTopicName;

    private final StreamBridge streamBridge;

    public EstoqueInsuficienteStreamDispatcher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Override
    public void enviar(Long pedidoId) {
        log.info("Estoque Insuficiente - enviado para '{}':  {}", estoqueInsuficienteTopicName, pedidoId);
        streamBridge.send(estoqueInsuficienteTopicName, pedidoId);
    }

}
