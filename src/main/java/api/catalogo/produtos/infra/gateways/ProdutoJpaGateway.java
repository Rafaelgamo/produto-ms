package api.catalogo.produtos.infra.gateways;

import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.domain.entity.Produto;
import api.catalogo.produtos.infra.dto.ProdutoDTO;
import api.catalogo.produtos.infra.gateways.mensageria.dispatcher.EstoqueInsuficienteDispatcher;
import api.catalogo.produtos.infra.gateways.mensageria.dispatcher.EstoqueReservadoDispatcher;
import api.catalogo.produtos.infra.persistence.ProdutoEntity;
import api.catalogo.produtos.infra.persistence.ProdutoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoJpaGateway implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;
    private final EstoqueReservadoDispatcher estoqueReservadoDispatcher;
    private final EstoqueInsuficienteDispatcher estoqueInsuficienteDispatcher;

    private final ProdutoEntityMapper produtoEntityMapper;

    public ProdutoJpaGateway(ProdutoRepository produtoRepository, EstoqueReservadoDispatcher estoqueReservadoDispatcher, EstoqueInsuficienteDispatcher estoqueInsuficienteDispatcher, ProdutoEntityMapper produtoEntityMapper) {
        this.produtoRepository = produtoRepository;
        this.estoqueReservadoDispatcher = estoqueReservadoDispatcher;
        this.estoqueInsuficienteDispatcher = estoqueInsuficienteDispatcher;
        this.produtoEntityMapper = produtoEntityMapper;
    }

    @Override
    public Produto cadastrarProduto(Produto produto) {
        ProdutoEntity entity = produtoEntityMapper.toEntity(produto);
        produtoRepository.save(entity);
        Produto produtoSalvo = produtoEntityMapper.toDomain(entity);
        return produtoSalvo;
    }

    @Override
    public List<ProdutoDTO> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(produtoEntityMapper::toDTO)
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
    public void salvarReservaEstoque(Long pedidoId, List<ProdutoDTO> produtos) {
        var produtoEntitys = produtos.stream()
                .map(produtoEntityMapper::toEntity)
                .collect(Collectors.toList());

        produtoRepository.saveAll(produtoEntitys);
        estoqueReservadoDispatcher.enviar(pedidoId);
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        var entidade = produtoRepository.findById(id).orElse(null);
        if (entidade == null) {
            return Optional.empty();
        }

        return Optional.of(produtoEntityMapper.toDomain(entidade));
    }

    @Override
    public void atualizarProduto(Long id, Produto produto) {
        var entidade = produtoRepository.findById(id).orElse(null);
        if(entidade == null){
            return;
        }

        if(produto.getNome() != null)               entidade.setNome(produto.getNome());
        if(produto.getTipo() != null)               entidade.setTipo(produto.getTipo());
        if(produto.getDescricao() != null)          entidade.setDescricao(produto.getDescricao());
        if(produto.getValor() != null)              entidade.setValor(produto.getValor());
        if(produto.getQuantidadeEstoque() != null)  entidade.setQuantidadeEstoque(produto.getQuantidadeEstoque());

        produtoRepository.save(entidade);
    }

    @Override
    public void notificarEstoqueInsuficiente(Long pedidoId) {
        estoqueInsuficienteDispatcher.enviar(pedidoId);
    }
}

