package api.catalogo.produtos.DomainProduto.TesteUseCase;

import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.application.usecases.AlterarProdutoUseCase;
import api.catalogo.produtos.application.usecases.BuscarProdutoUseCase;
import api.catalogo.produtos.application.usecases.CadastrarProdutoUseCase;
import api.catalogo.produtos.application.usecases.ExcluirProdutoUseCase;
import api.catalogo.produtos.infra.controller.ProdutoController;
import api.catalogo.produtos.infra.dto.AlteraProdutoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class AlterarProdutoUseCaseTest {


    private ProdutoController produtoController;
    private AlterarProdutoUseCase alterarProdutoUseCase;
    private ProdutoGateway produtoGateway;
    private AlteraProdutoDTO alteraProdutoDTO;

    private Long id = 1L;

    @BeforeEach
    void setUp() {


    }


}

