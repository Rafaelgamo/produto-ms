package api.catalogo.produtos.infra.gateways;

import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.domain.Produto;
import api.catalogo.produtos.exceptions.ReservaEstoqueException;
import api.catalogo.produtos.infra.dto.ProdutoDTO;
import api.catalogo.produtos.infra.gateways.mensageria.dispatcher.EstoqueInsuficienteDispatcher;
import api.catalogo.produtos.infra.gateways.mensageria.dispatcher.EstoqueReservadoDispatcher;
import api.catalogo.produtos.infra.persistence.PedidoProdutoRepository;
import api.catalogo.produtos.infra.persistence.ProdutoEntity;
import api.catalogo.produtos.infra.persistence.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoJpaGateway implements ProdutoGateway {

    private static final Logger log = LoggerFactory.getLogger(ProdutoJpaGateway.class);
    private final ProdutoRepository produtoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;
    private final EstoqueReservadoDispatcher estoqueReservadoDispatcher;
    private final EstoqueInsuficienteDispatcher estoqueInsuficienteDispatcher;

    private final ProdutoEntityMapper produtoEntityMapper;

    public ProdutoJpaGateway(ProdutoRepository produtoRepository, PedidoProdutoRepository pedidoProdutoRepository, EstoqueReservadoDispatcher estoqueReservadoDispatcher, EstoqueInsuficienteDispatcher estoqueInsuficienteDispatcher, ProdutoEntityMapper produtoEntityMapper) {
        this.produtoRepository = produtoRepository;
        this.pedidoProdutoRepository = pedidoProdutoRepository;
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
    public void descontarQuantidadeReservadaDosItensDoPedido(Long pedidoId) {
        var pedidoProdutos = pedidoProdutoRepository.findAllByPedido(pedidoId);

        for (var pedidoProduto : pedidoProdutos) {
            var produto = pedidoProduto.getProduto();

            var quantidadeReservada = produto.getQuantidadeReservada();

            var novaQuantidadeReservada = quantidadeReservada - pedidoProduto.getQuantidade();
            if (novaQuantidadeReservada < 0) {
                log.warn("Erro ao descontar da quantidade reservada: pedidoId={}", pedidoId);
                throw new ReservaEstoqueException("Erro ao descontar da quantidade reservada: pedidoId=" + pedidoId);
            }

            produto.setQuantidadeReservada(novaQuantidadeReservada);
        }
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

