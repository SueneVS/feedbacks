package com.fullstack.feedbacks.infra;

import com.fullstack.feedbacks.dtos.ErrosDto;
import com.fullstack.feedbacks.infra.exceptions.ExceptionNotFound;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrosDto.class))
            ),
    })
    @ExceptionHandler(ExceptionNotFound.class)
    public ResponseEntity handler(ExceptionNotFound e){
        log.error("Ocorreu um erro: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrosDto.builder()
                        .erro(e.getClass().getSimpleName())
                        .codigo("404")
                        .mensagem(e.getMessage())
                        .build()
                );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity handler(Exception e) {
        log.error("Falha catastrófica {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrosDto.builder()
                        .erro(e.getClass().getSimpleName())
                        .codigo("500")
                        .mensagem(e.getMessage())
                        .build()
        );
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "422",
                    description = "Falha no processamento",
                    content = @Content(schema = @Schema(implementation = ErrosDto.class))
            ),
    })

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handler1(MethodArgumentNotValidException e) {
        String msg = "";
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            msg += error.getField() + ": " + error.getDefaultMessage() + "; ";
        }
        msg = msg.trim();
        log.error("ERRO de validação: {}", msg);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                ErrosDto.builder()
                        .erro(e.getClass().getSimpleName())
                        .codigo("422")
                        .mensagem(msg)
                        .build()
        );
    }
}
