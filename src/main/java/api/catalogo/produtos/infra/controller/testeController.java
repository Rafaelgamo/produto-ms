package api.catalogo.produtos.infra.controller;

import api.catalogo.produtos.application.usecases.BaixarEstoqueUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teste")
public class testeController {

    private final BaixarEstoqueUseCase baixarEstoqueUseCase;

    public testeController(BaixarEstoqueUseCase baixarEstoqueUseCase) {
        this.baixarEstoqueUseCase = baixarEstoqueUseCase;
    }

    @PostMapping
    public void tesste(@RequestBody teste teste){
        baixarEstoqueUseCase.reservaEstoque(teste.pedidoId(), teste.quantidades());
    }
}
