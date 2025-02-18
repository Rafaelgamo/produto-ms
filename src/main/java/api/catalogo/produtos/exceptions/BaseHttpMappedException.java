package api.catalogo.produtos.exceptions;

import org.springframework.http.HttpStatus;

public abstract class BaseHttpMappedException extends RuntimeException {

    public abstract HttpStatus getHttpStatus();

    public BaseHttpMappedException(String message) {
        super(message);
    }
}
