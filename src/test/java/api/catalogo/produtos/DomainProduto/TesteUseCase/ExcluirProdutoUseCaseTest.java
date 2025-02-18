package api.catalogo.produtos.DomainProduto.TesteUseCase;

import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.application.usecases.AlterarProdutoUseCase;
import api.catalogo.produtos.application.usecases.BuscarProdutoUseCase;
import api.catalogo.produtos.application.usecases.CadastrarProdutoUseCase;
import api.catalogo.produtos.application.usecases.ExcluirProdutoUseCase;
import api.catalogo.produtos.infra.controller.ProdutoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.http.HttpStatus.OK;

public class ExcluirProdutoUseCaseTest {

    private CadastrarProdutoUseCase cadastrarProdutoUseCase;
    private BuscarProdutoUseCase buscarProdutoUseCase;
    private ExcluirProdutoUseCase excluirProdutoUseCase;
    private AlterarProdutoUseCase alterarProdutoUseCase;

    private ProdutoController produtoController;
    private ProdutoGateway produtoGateway;

    @BeforeEach
    void setUp() {
        this.buscarProdutoUseCase = mock(BuscarProdutoUseCase.class);
        this.alterarProdutoUseCase = mock(AlterarProdutoUseCase.class);
        this.cadastrarProdutoUseCase = mock(CadastrarProdutoUseCase.class);
        this.excluirProdutoUseCase = mock(ExcluirProdutoUseCase.class);

        this.produtoController = new ProdutoController(cadastrarProdutoUseCase, buscarProdutoUseCase, excluirProdutoUseCase, alterarProdutoUseCase);

        this.produtoGateway = mock(ProdutoGateway.class);
    }

    @Test
    void excluirProduto() {
        var response = assertDoesNotThrow(() -> produtoController.excluirProduto(1L));
        assertEquals(ResponseEntity.noContent().build(), response);
    }
}
