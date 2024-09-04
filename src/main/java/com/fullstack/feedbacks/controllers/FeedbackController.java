package com.fullstack.feedbacks.controllers;
import com.fullstack.feedbacks.dtos.FeedbackReqDto;
import com.fullstack.feedbacks.dtos.FeedbackResDto;
import com.fullstack.feedbacks.dtos.RespostaReqDto;
import com.fullstack.feedbacks.dtos.RespostaResDto;
import com.fullstack.feedbacks.facades.FeedbackFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("feedbacks")
public class FeedbackController {

    private final FeedbackFacade facade;

    @Operation(summary = "Criar novo feedback")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Feedback criado",
                    useReturnTypeSchema = true
            ),
    })

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FeedbackResDto post(@Valid @RequestBody FeedbackReqDto feedbackReqdto) {
        log.info("POST /feedbacks -> Begin");
        return facade.criar(feedbackReqdto);
    }

    @Operation(summary = "Buscar feedback")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Feedback encontrado",
                    useReturnTypeSchema = true
            ),
    })


    @GetMapping
    public List<FeedbackResDto> get(
            @Parameter(description = "Perquisar por título (que contenha o valor: `%{valor}%`)")
            @RequestParam(required = false) String titulo
    ) {
        log.info("GET /feedbacks -> Begin");
        return facade.buscarTodos(titulo);
    }

    @Operation(summary = "Buscar feedback por ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Feedback encontrado",
                    useReturnTypeSchema = true
            ),
    })


    @GetMapping("{id}")
    public FeedbackResDto getId(
            @Parameter(description = "ID do feedback")
            @PathVariable Long id
    ) {
        log.info("GET /feedbacks/{} -> Begin", id);
        return facade.buscarPorId(id);
    }

    @Operation(summary = "Criar novo retorno ao feedback")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Retorno criado",
                    useReturnTypeSchema = true
            ),
    })

    @PostMapping("{id}/feedbacks")
    public RespostaResDto postResposta(
            @Parameter(description = "ID do feedback")
            @PathVariable Long id,
            @Valid @RequestBody RespostaReqDto respostaReqDto
    ) {
        log.info("POST /feedbacks/{id}/feedbacks -> Begin");
        return facade.criarResposta(id, respostaReqDto);
    }

}