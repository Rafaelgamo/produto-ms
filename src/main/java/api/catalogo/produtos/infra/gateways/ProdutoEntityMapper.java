package api.catalogo.produtos.infra.gateways;

import api.catalogo.produtos.domain.entity.Produto;
import api.catalogo.produtos.infra.dto.ProdutoDTO;
import api.catalogo.produtos.infra.persistence.ProdutoEntity;

public class ProdutoEntityMapper {

    public ProdutoEntity toEntity(Produto produto) {
        return new ProdutoEntity(
                produto.getNome(),
                produto.getTipo(),
                produto.getDescricao(),
                produto.getValor(),
                produto.getQuantidadeEstoque(),
                produto.getQuantidadeReservada(),
                produto.getHoraImportacao()


        );
    }

    public Produto toDomain(ProdutoEntity produtoEntity) {
        return new Produto(
                produtoEntity.getNome(),
                produtoEntity.getTipo(),
                produtoEntity.getDescricao(),
                produtoEntity.getValor(),
                produtoEntity.getQuantidadeEstoque(),
                produtoEntity.getQuantidadeReservada(),
                produtoEntity.getHoraImportacao()
        );

    }

    public ProdutoDTO toDTO(ProdutoEntity produtoEntity) {
        return new ProdutoDTO(
                produtoEntity.getId(),
                produtoEntity.getNome(),
                produtoEntity.getTipo(),
                produtoEntity.getDescricao(),
                produtoEntity.getValor(),
                produtoEntity.getQuantidadeEstoque(),
                produtoEntity.getQuantidadeReservada(),
                produtoEntity.getHoraImportacao()
        );
    }

    public ProdutoEntity toEntity(ProdutoDTO produtoDTO) {
        return new ProdutoEntity(
                produtoDTO.getId(),
                produtoDTO.getNome(),
                produtoDTO.getTipo(),
                produtoDTO.getDescricao(),
                produtoDTO.getValor(),
                produtoDTO.getQuantidadeEstoque(),
                produtoDTO.getQuantidadeReservada(),
                produtoDTO.getHoraImportacao()


        );
    }


}
