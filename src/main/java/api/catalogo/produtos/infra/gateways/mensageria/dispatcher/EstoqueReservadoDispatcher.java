package api.catalogo.produtos.infra.gateways.mensageria.dispatcher;

public interface EstoqueReservadoDispatcher {

    void enviar(Long pedidoId);
}
