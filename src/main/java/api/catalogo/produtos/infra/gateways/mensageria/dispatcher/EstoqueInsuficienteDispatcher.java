package api.catalogo.produtos.infra.gateways.mensageria.dispatcher;

public interface EstoqueInsuficienteDispatcher {
    void enviar(Long pedidoId);
}
