package api.catalogo.produtos.infra.controller;


import api.catalogo.produtos.application.usecases.BuscarProdutoUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/busca")
public class ListaController {

    private final BuscarProdutoUseCase buscarProdutoUseCase;

    public ListaController(BuscarProdutoUseCase buscarProdutoUseCase) {
        this.buscarProdutoUseCase = buscarProdutoUseCase;
    }



}
