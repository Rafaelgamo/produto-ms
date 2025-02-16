package api.catalogo.produtos.infra.dto;

import java.util.Map;

public record ListaProdutoDTO(
        Long id,
        String nome,
        String valor
) {

    public ListaProdutoDTO(Long id, String nome, String valor) {
        this.id= id;
        this.nome = nome;
        this.valor = valor;

    }
}
