package com.fullstack.feedbacks.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Resposta do feedback - Objeto para criação")
public class RespostaReqDto {

    @NotBlank
    @Schema(description = "Texto da resposta do feedback", example = "Concordo!!!")
    private String retorno;
}
