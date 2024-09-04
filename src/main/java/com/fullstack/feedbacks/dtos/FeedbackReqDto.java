package com.fullstack.feedbacks.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "Feedbacks - Objeto de requisição")
public class FeedbackReqDto {

    @Schema(description = "Titulo do feedback", example = "Excelente loja")
    private String titulo;

    @Schema(description = "Descrição do feedback", example = "A loja tem ótimos preços e atendimento excelente")
    private String descricao;
}
