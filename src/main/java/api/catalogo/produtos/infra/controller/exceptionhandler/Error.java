package api.catalogo.produtos.infra.controller.exceptionhandler;

import org.springframework.http.HttpStatus;

public record Error (Integer errorCode, String message) {
    public Error(HttpStatus httpStatus, String message) { this(httpStatus.value(), message); }
    public Error(String message) {	this(HttpStatus.INTERNAL_SERVER_ERROR.value(), message); }

}
