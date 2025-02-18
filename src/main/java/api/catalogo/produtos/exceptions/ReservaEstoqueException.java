package api.catalogo.produtos.exceptions;

import org.springframework.http.HttpStatus;

public class ReservaEstoqueException extends BaseHttpMappedException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    public ReservaEstoqueException(String mensagem){
        super(mensagem);
    }

}
