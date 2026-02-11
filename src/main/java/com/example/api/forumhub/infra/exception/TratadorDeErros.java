package com.example.api.forumhub.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(EntityNotFoundException ex) {
        var mensagem = ex.getMessage() != null && !ex.getMessage().isEmpty() 
                ? ex.getMessage() 
                : "Recurso não encontrado";
        
        var erro = new DadosErro("Not Found", mensagem);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity tratarErro400(MethodArgumentTypeMismatchException ex) {
        if (ex.getParameter().getParameterName().equals("id") || ex.getParameter().getParameterName().equals("idResposta")) {
            var erro = new DadosErro("Bad Request", "Id inválido");
            return ResponseEntity.badRequest().body(erro);
        }
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors().stream()
                .map(DadosErroValidacao::new)
                .toList();
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException ex) {
        var erro = new DadosErro("Bad Request", ex.getMessage());
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(SemPermissaoException.class)
    public ResponseEntity tratarErroSemPermissao(SemPermissaoException ex) {
        var erro = new DadosErro("Forbidden", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
    }

    private record DadosErro(String error, String message) {}

    private record DadosErroValidacao(String field, String message) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
