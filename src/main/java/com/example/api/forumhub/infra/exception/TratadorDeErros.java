package com.example.api.forumhub.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity tratarErro400(MethodArgumentTypeMismatchException ex) {
        if (ex.getParameter().getParameterName().equals("id")) {
            return ResponseEntity.badRequest().body("Id inválido");
        }
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
