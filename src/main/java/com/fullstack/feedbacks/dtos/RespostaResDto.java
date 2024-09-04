package com.fullstack.feedbacks.dtos;

import com.fullstack.feedbacks.entities.RespostaEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@Schema(description = "Resposta ao feedback - Objeto de resposta")
public class RespostaResDto {
    @Schema(description = "ID da resposta", example = "1")
    private Long id;

    @Schema(description = "Data em que a resposta foi enviada")
    private LocalDateTime dataEnvio;

    @Schema(description = "Texto da resposta do feedback", example = "Realmente!")
    private String retorno;

    public RespostaResDto(RespostaEntity respostaEntity) {
        BeanUtils.copyProperties(respostaEntity, this);
    }
}
