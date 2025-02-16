package api.catalogo.produtos.infra.dto;

import api.catalogo.produtos.application.json.AlterarProdutoJson;

public record AlteraProdutoDTO(
        String nome,
        String tipo,
        String descricao,
        String valor,
        Double quantidadeEstoque

) {

    public AlteraProdutoDTO(AlterarProdutoJson alterarProdutoJson) {
        this(
                alterarProdutoJson.nome(),
                alterarProdutoJson.tipo(),
                alterarProdutoJson.descricao(),
                alterarProdutoJson.valor(),
                alterarProdutoJson.quantidadeEstoque()

        );

    }
}