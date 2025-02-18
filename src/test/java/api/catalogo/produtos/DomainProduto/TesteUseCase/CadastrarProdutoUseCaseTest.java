package api.catalogo.produtos.DomainProduto.TesteUseCase;

import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.application.usecases.AlterarProdutoUseCase;
import api.catalogo.produtos.application.usecases.BuscarProdutoUseCase;
import api.catalogo.produtos.application.usecases.CadastrarProdutoUseCase;
import api.catalogo.produtos.application.usecases.ExcluirProdutoUseCase;
import api.catalogo.produtos.domain.entity.Produto;
import api.catalogo.produtos.infra.controller.ProdutoController;
import api.catalogo.produtos.infra.dto.ProdutoDTO;
import api.catalogo.produtos.infra.persistence.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;

public class CadastrarProdutoUseCaseTest {


    private CadastrarProdutoUseCase cadastrarProdutoUseCase;
    private BuscarProdutoUseCase buscarProdutoUseCase;
    private ExcluirProdutoUseCase excluirProdutoUseCase;
    private AlterarProdutoUseCase alterarProdutoUseCase;

    private ProdutoDTO produtoDTO;
    private ProdutoController produtoController;
    private ProdutoGateway produtoGateway;



    @BeforeEach
    void setUp() {
        this.produtoGateway = mock(ProdutoGateway.class);
        this.cadastrarProdutoUseCase = new CadastrarProdutoUseCase(produtoGateway);
        this.produtoDTO = mock(ProdutoDTO.class);

        this.produtoController = new ProdutoController(cadastrarProdutoUseCase, buscarProdutoUseCase, excluirProdutoUseCase, alterarProdutoUseCase);

    }
    
}
