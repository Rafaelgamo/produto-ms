package api.catalogo.produtos.infra.gateways.mensageria.mensagens;

public record QuantidadeItemMensagem(
        Long itemId,
        Double quantidade

) {
}
