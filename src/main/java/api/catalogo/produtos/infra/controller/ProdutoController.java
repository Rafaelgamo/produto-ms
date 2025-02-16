package api.catalogo.produtos.infra.controller;


import api.catalogo.produtos.application.usecases.*;
import api.catalogo.produtos.domain.entity.Produto;
import api.catalogo.produtos.infra.dto.AlteraProdutoDTO;
import api.catalogo.produtos.infra.dto.ListaProdutoDTO;
import api.catalogo.produtos.infra.dto.ProdutoDTO;
import api.catalogo.produtos.infra.persistence.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

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
    public List<ProdutoDTO> listarPedidos() {
        return buscarProdutoUseCase.listarProdutos().stream()
                .map(p -> new ProdutoDTO(null, p.getNome(), p.getTipo(), p.getDescricao(), p.getValor(), p.getQuantidadeEstoque(), p.getQuantidadeReservada(), LocalDateTime.now()))
                .collect(Collectors.toList());
    }


    @DeleteMapping("/{id}")
    public void excluirProduto(@PathVariable Long id) {
        excluirProdutoUseCase.excluirProduto(id);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity alterarProduto(@PathVariable Long id, @RequestBody @Valid AlteraProdutoDTO data) {
        alterarProdutoUseCase.alterarProduto(id, data);
        return null;
    }




}



