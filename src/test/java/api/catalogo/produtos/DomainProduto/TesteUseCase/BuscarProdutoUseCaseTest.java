package api.catalogo.produtos.DomainProduto.TesteUseCase;

import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.application.usecases.AlterarProdutoUseCase;
import api.catalogo.produtos.application.usecases.BuscarProdutoUseCase;
import api.catalogo.produtos.application.usecases.CadastrarProdutoUseCase;
import api.catalogo.produtos.application.usecases.ExcluirProdutoUseCase;
import api.catalogo.produtos.infra.controller.ProdutoController;
import api.catalogo.produtos.infra.dto.ProdutoDTO;
import api.catalogo.produtos.infra.persistence.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class BuscarProdutoUseCaseTest {

    private BuscarProdutoUseCase buscarProdutoUseCase;
    private AlterarProdutoUseCase alterarProdutoUseCase;
    private CadastrarProdutoUseCase cadastrarProdutoUseCase;
    private ExcluirProdutoUseCase excluirProdutoUseCase;

    private ProdutoController produtoController;
    private ProdutoGateway produtoGateway;
    private ProdutoRepository produtoRepository;

    private ProdutoDTO produto = new ProdutoDTO(1L, "nome", "tipo", "descricao", 35d, 100.0, 0.0, LocalDateTime.now());

    private Long id = 1L;
    private final List<Long> itens = List.of(id);

    @BeforeEach
    void setUp() {
        this.produtoGateway = mock(ProdutoGateway.class);
        this.buscarProdutoUseCase = new BuscarProdutoUseCase(produtoGateway);

        this.alterarProdutoUseCase = mock(AlterarProdutoUseCase.class);
        this.cadastrarProdutoUseCase = mock(CadastrarProdutoUseCase.class);
        this.excluirProdutoUseCase = mock(ExcluirProdutoUseCase.class);

        this.produtoController = new ProdutoController(cadastrarProdutoUseCase, buscarProdutoUseCase, excluirProdutoUseCase, alterarProdutoUseCase);

        this.produtoRepository = mock(ProdutoRepository.class);
    }

    @Test
    void listarProdutos() {
        var lista = assertDoesNotThrow(() -> produtoController.listarProdutos(new Long[]{1L}));
        Mockito.when(produtoGateway.listarPorIds(itens)).thenReturn(List.of(produto));

        verify(produtoGateway, times(1)).listarPorIds(itens);
        assertEquals(ResponseEntity.ok(List.of()), lista);
    }

}
