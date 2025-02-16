package api.catalogo.produtos.application.json;

public record AlterarProdutoJson (

        String nome,
        String tipo,
        String descricao,
        String valor,
        Double quantidadeEstoque,
        Double quantidadeReservada

) {
}
