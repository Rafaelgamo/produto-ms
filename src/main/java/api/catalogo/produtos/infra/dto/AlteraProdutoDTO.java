package api.catalogo.produtos.infra.dto;

public record AlteraProdutoDTO(
        String nome,
        String tipo,
        String descricao,
        String valor,
        Double quantidadeEstoque

) {


}