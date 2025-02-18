package api.catalogo.produtos.DomainProduto.TesteUseCase;

import api.catalogo.produtos.application.gateways.ProdutoGateway;
import api.catalogo.produtos.application.usecases.AlterarProdutoUseCase;
import api.catalogo.produtos.application.usecases.BuscarProdutoUseCase;
import api.catalogo.produtos.application.usecases.CadastrarProdutoUseCase;
import api.catalogo.produtos.application.usecases.ExcluirProdutoUseCase;
import api.catalogo.produtos.domain.entity.Produto;
import api.catalogo.produtos.infra.controller.ProdutoController;
import api.catalogo.produtos.infra.dto.AlteraProdutoDTO;
import api.catalogo.produtos.infra.dto.ProdutoDTO;
import api.catalogo.produtos.infra.gateways.ProdutoJpaGateway;
import api.catalogo.produtos.infra.persistence.ProdutoRepository;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

        produto = new Produto("Mesa", "Movel de Madeira", "largura x, Tamanho y", "100", 15.0, 0.0, LocalDateTime.now());
        produtoDTO = new ProdutoDTO(1L,"Mesa", "Movel de Madeira", "largura x, Tamanho y", "100", 15.0, 0.0, LocalDateTime.now());


    }

    @Test
    void setCadastrarProdutoUseCase(){

        var teste = assertDoesNotThrow(() -> produtoController.cadastrarProduto(produtoDTO));
        when(produtoGateway.cadastrarProduto(produto)).thenReturn(produto);
      //  assertEquals(produtoDTO.getNome(), produto.getTipo().getNome());
    }
    
}
