package com.picpaysimplificado.infra;

import com.picpaysimplificado.domain.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.valves.rewrite.ResolverImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity treatDuplicateEntry(DataIntegrityViolationException exception){
        ExceptionDTO exceptionDTO =  new ExceptionDTO("Usuário já cadastrado", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity treat404(DataIntegrityViolationException exception){
           return ResponseEntity.badRequest().build();

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(EntityNotFoundException exception){
        ExceptionDTO exceptionDTO =  new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }
}
