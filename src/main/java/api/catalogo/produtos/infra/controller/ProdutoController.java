package api.catalogo.produtos.infra.controller;


import api.catalogo.produtos.application.usecases.AlterarProdutoUseCase;
import api.catalogo.produtos.application.usecases.BuscarProdutoUseCase;
import api.catalogo.produtos.application.usecases.CadastrarProdutoUseCase;
import api.catalogo.produtos.application.usecases.ExcluirProdutoUseCase;
import api.catalogo.produtos.domain.entity.Produto;
import api.catalogo.produtos.infra.dto.AlteraProdutoDTO;
import api.catalogo.produtos.infra.dto.ProdutoDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final CadastrarProdutoUseCase cadastrarProdutoUseCase;
    private final BuscarProdutoUseCase buscarProdutoUseCase;
    private final ExcluirProdutoUseCase excluirProdutoUseCase;
    private final AlterarProdutoUseCase alterarProdutoUseCase;


    public ProdutoController(CadastrarProdutoUseCase cadastrarProdutoUseCase,
                             BuscarProdutoUseCase buscarProdutoUseCase,
                             ExcluirProdutoUseCase excluirProdutoUseCase,
                             AlterarProdutoUseCase alterarProdutoUseCase) {
        this.cadastrarProdutoUseCase = cadastrarProdutoUseCase;
        this.buscarProdutoUseCase = buscarProdutoUseCase;
        this.excluirProdutoUseCase = excluirProdutoUseCase;


        this.alterarProdutoUseCase = alterarProdutoUseCase;
    }

    @PostMapping
    public ProdutoDTO cadastrarProduto(@RequestBody ProdutoDTO produtoDto) {
        Produto produtoSalvo = cadastrarProdutoUseCase.cadastratProduto(new Produto(produtoDto.getNome(), produtoDto.getTipo(),
                produtoDto.getDescricao(), produtoDto.getValor(), produtoDto.getQuantidadeEstoque(), produtoDto.getQuantidadeReservada(), LocalDateTime.now()));

        return new ProdutoDTO(null, produtoSalvo.getNome(), produtoSalvo.getTipo(), produtoSalvo.getDescricao(),
                produtoSalvo.getValor(), produtoSalvo.getQuantidadeEstoque(), produtoSalvo.getQuantidadeReservada(), LocalDateTime.now());
    }


    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos(@RequestParam(required = false) Long[] ids) {
        if (ids == null || ids.length == 0) {
             var todos = buscarProdutoUseCase.listarProdutos();
             return ResponseEntity.ok(todos);
        } else {
            var filtrados = buscarProdutoUseCase.listarProdutos(List.of(ids));
            return ResponseEntity.ok(filtrados);
        }
    }


    @DeleteMapping("/{id}")
    public void excluirProduto(@PathVariable Long id) {
        excluirProdutoUseCase.excluirProduto(id);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> alterarProduto(@PathVariable Long id, @RequestBody @Valid AlteraProdutoDTO data) {
        alterarProdutoUseCase.alterarProduto(id, data);
        return ResponseEntity.noContent().build();
    }




}



