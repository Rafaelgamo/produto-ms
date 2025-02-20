package api.catalogo.produtos.DomainProduto.TesteUseCase;

import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.application.usecases.AlterarProdutoUseCase;
import api.catalogo.produtos.application.usecases.BuscarProdutoUseCase;
import api.catalogo.produtos.application.usecases.CadastrarProdutoUseCase;
import api.catalogo.produtos.application.usecases.ExcluirProdutoUseCase;
import api.catalogo.produtos.domain.Produto;
import api.catalogo.produtos.infra.controller.ProdutoController;
import api.catalogo.produtos.infra.dto.ProdutoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;

public class CadastrarProdutoUseCaseTest {


    private CadastrarProdutoUseCase cadastrarProdutoUseCase;
    private BuscarProdutoUseCase buscarProdutoUseCase;
    private ExcluirProdutoUseCase excluirProdutoUseCase;
    private AlterarProdutoUseCase alterarProdutoUseCase;

    private ProdutoDTO produtoDTO;
    private Produto produto;
    private ProdutoController produtoController;
    private ProdutoGateway produtoGateway;




    @BeforeEach
    void setUp() {
        this.produtoGateway = mock(ProdutoGateway.class);
        this.cadastrarProdutoUseCase = new CadastrarProdutoUseCase(produtoGateway);
        this.produto = mock(Produto.class);
        this.produtoDTO = mock(ProdutoDTO.class);

        this.produtoController = new ProdutoController(cadastrarProdutoUseCase, buscarProdutoUseCase, excluirProdutoUseCase, alterarProdutoUseCase);

        produto = new Produto("Mesa", "Movel de Madeira", "largura x, Tamanho y", 100d, 15.0, 0.0, LocalDateTime.now());
        produtoDTO = new ProdutoDTO(1L,"Mesa", "Movel de Madeira", "largura x, Tamanho y", 100d, 15.0, 0.0, LocalDateTime.now());

    }

    @Test
    void setCadastrarProdutoUseCase(){

        var teste = assertDoesNotThrow(() -> produtoController.cadastrarProduto(produtoDTO));
        when(produtoGateway.cadastrarProduto(produto)).thenReturn(produto);
        assertEquals(ResponseEntity.status(CREATED).build(), teste);
    }
    
}
