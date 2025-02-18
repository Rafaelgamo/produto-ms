package api.catalogo.produtos.infra.gateways.mensageria.dispatcher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class EstoqueInsuficienteStreamDispatcher implements EstoqueInsuficienteDispatcher {

    @Value("${queue.topics.estoque-insuficiente.name}")
    private String estoqueInsuficienteTopicName;

    private final StreamBridge streamBridge;

    public EstoqueInsuficienteStreamDispatcher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Override
    public void enviar(Long pedidoId) {
        streamBridge.send(estoqueInsuficienteTopicName, pedidoId);
    }

}
