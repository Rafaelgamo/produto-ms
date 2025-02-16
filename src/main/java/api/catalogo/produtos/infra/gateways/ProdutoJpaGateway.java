package api.catalogo.produtos.infra.gateways;

import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.domain.entity.Produto;
import api.catalogo.produtos.infra.dto.ListaProdutoDTO;
import api.catalogo.produtos.infra.dto.ProdutoDTO;
import api.catalogo.produtos.infra.persistence.ProdutoEntity;
import api.catalogo.produtos.infra.persistence.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoJpaGateway implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;
    private final ProdutoEntityMapper produtoEntityMapper;

    public ProdutoJpaGateway(ProdutoRepository produtoRepository, ProdutoEntityMapper produtoEntityMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoEntityMapper = produtoEntityMapper;

    }

    @Override
    public Produto cadastrarProduto(Produto produto) {
        ProdutoEntity entity = produtoEntityMapper.toEntity(produto);
        produtoRepository.save(entity);
        Produto produtoSolavo = produtoEntityMapper.toDomain(entity);
        return produtoSolavo;
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(produtoEntityMapper::toDomain)
                .collect(Collectors.toList());
    }


    @Override
    public void excluirProduto(Long id) {
        Optional<ProdutoEntity> entity = produtoRepository.findById(id);
        produtoRepository.deleteById(id);
    }

    @Override
    public List<ProdutoDTO> listarPorIds(Collection<Long> ids) {
        var produtoEntitys = produtoRepository.findAllById(ids);
        return produtoEntitys.stream()
                .map(produtoEntityMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public void atualizarQuantidades(List<ProdutoDTO> produtos) {
        var produtoEntitys = produtos.stream()
                .map(produtoEntityMapper::toEntity)
                .collect(Collectors.toList());

        produtoRepository.saveAll(produtoEntitys);
    }



    @Override
    public Optional<Produto> buscarPorId(Long id) {
        var entidade = produtoRepository.findById(id).orElse(null);
        if(entidade == null){
            return Optional.empty();
        }

        return Optional.of(produtoEntityMapper.toDomain(entidade));
    }

    @Override
    public void atualizarProduto(Long id, Produto produto) {
        var entidade = produtoRepository.findById(id).orElse(null);
        if(entidade == null){
            return ;
        }
        if(produto.getNome() != null) entidade.setNome(produto.getNome());
        if(produto.getTipo() != null)entidade.setTipo(produto.getTipo());
        if(produto.getDescricao() != null)entidade.setDescricao(produto.getDescricao());
        if(produto.getValor() != null)entidade.setValor(produto.getValor());
        if(produto.getQuantidadeEstoque() != null)entidade.setQuantidadeEstoque(produto.getQuantidadeEstoque());

        produtoRepository.save(entidade);
    }

}

