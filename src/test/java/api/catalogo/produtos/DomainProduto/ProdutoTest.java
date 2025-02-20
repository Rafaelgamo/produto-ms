package api.catalogo.produtos.DomainProduto;

import api.catalogo.produtos.domain.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


public class ProdutoTest {

    @Test
    public void nãoDeveCadastrarNomeVazio(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Produto("", "Periferico", "Descricao", 100d, 100.00,100.00, LocalDateTime.now()));

    }
    @Test
    public void nãoDeveCadastrarTipoVazio(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Produto("Teste", "", "Descricao", 100d, 100.00,100.00, LocalDateTime.now()));

    }
    @Test
    public void nãoDeveCadastrarDescricaoVazio(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Produto("Teste", "Periferico", "", 100d, 100.00,100.00, LocalDateTime.now()));

    }
    @Test
    public void nãoDeveCadastrarValorVazio(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Produto("Teste", "Periferico", "Descricao", null, 100.00,100.00, LocalDateTime.now()));

    }

    @Test
    public void nãoDeveCadastrarQuantidadeZero(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Produto("Teste", "Periferico", "Descricao", 100d, -1.0,100.00, LocalDateTime.now()));

    }

}
