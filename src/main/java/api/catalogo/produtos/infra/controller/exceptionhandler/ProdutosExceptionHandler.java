package api.catalogo.produtos.infra.controller.exceptionhandler;

import api.catalogo.produtos.exceptions.BaseHttpMappedException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.NoSuchElementException;

@Hidden
@RestControllerAdvice
public class ProdutosExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ProdutosExceptionHandler.class);

    @ExceptionHandler({IllegalArgumentException.class, HandlerMethodValidationException.class, HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Error> handleBadRequest(HttpServletRequest req, HttpMessageNotReadableException ex) {
        log(HttpStatus.BAD_REQUEST, req.getRequestURI());
        return ResponseEntity.badRequest().body(new Error(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Error> notFoundException(HttpServletRequest req, HttpMessageNotReadableException ex) {
        log(HttpStatus.NOT_FOUND, req.getRequestURI());
        return ResponseEntity.badRequest().body(new Error(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(BaseHttpMappedException.class)
    public ResponseEntity<Error> handleException(HttpServletRequest req, BaseHttpMappedException ex) {
        log(ex.getHttpStatus(), req.getRequestURI());
        return ResponseEntity.status(ex.getHttpStatus()).body(new Error(ex.getHttpStatus(), ex.getLocalizedMessage()));
    }

    private void log(HttpStatus status, String uri) {
        log.warn("{}: URL={}", status.name(), uri);
    }


}
