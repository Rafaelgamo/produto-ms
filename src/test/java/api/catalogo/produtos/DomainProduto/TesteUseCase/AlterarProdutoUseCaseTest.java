package api.catalogo.produtos.DomainProduto.TesteUseCase;

import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.application.usecases.AlterarProdutoUseCase;
import api.catalogo.produtos.application.usecases.BuscarProdutoUseCase;
import api.catalogo.produtos.application.usecases.CadastrarProdutoUseCase;
import api.catalogo.produtos.application.usecases.ExcluirProdutoUseCase;
import api.catalogo.produtos.domain.Produto;
import api.catalogo.produtos.infra.controller.ProdutoController;
import api.catalogo.produtos.infra.dto.AlteraProdutoDTO;
import api.catalogo.produtos.infra.dto.ProdutoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class AlterarProdutoUseCaseTest {

    private BuscarProdutoUseCase buscarProdutoUseCase;
    private AlterarProdutoUseCase alterarProdutoUseCase;
    private CadastrarProdutoUseCase cadastrarProdutoUseCase;
    private ExcluirProdutoUseCase excluirProdutoUseCase;

    private ProdutoGateway produtoGateway;
    private AlteraProdutoDTO alteraProdutoDTO;
    private ProdutoController produtoController;
    private final Long produtoId = 1L;


    private Produto produto;
    private ProdutoDTO produtoDTO;

    @BeforeEach
    void setUp() {

        this.alterarProdutoUseCase = mock(AlterarProdutoUseCase.class);
        this.produtoController = new ProdutoController(cadastrarProdutoUseCase, buscarProdutoUseCase, excluirProdutoUseCase, alterarProdutoUseCase);
        this.produtoGateway = mock(ProdutoGateway.class);


        alteraProdutoDTO = new AlteraProdutoDTO("", "", "largura x", null, 15.0);
    }

    @Test
    void atualizarProduto() {
        var retorno = assertDoesNotThrow(() -> produtoController.alterarProduto(produtoId, alteraProdutoDTO));
        assertEquals(ResponseEntity.noContent().build(), retorno);

    }
}

