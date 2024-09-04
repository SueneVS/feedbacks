package com.fullstack.feedbacks.dtos;

import com.fullstack.feedbacks.entities.FeedbackEntity;
import com.fullstack.feedbacks.entities.RespostaEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "Feedbacks - Objeto de resposta")
public class FeedbackResDto {

    @Schema(description = "ID do feedback", example = "1")
    private Long id;

    @Schema(description = "Titulo do feedback", example = "Excelente loja")
    private String titulo;

    @Schema(description = "Descrição do feedback", example = "A loja tem ótimos preços e atendimento excelente")
    private String descricao;

    @Schema(description = "Data que a sugestão foi enviada")
    private LocalDateTime dataEnvio;

    @Schema(description = "Data que a sugestão foi atualizada")
    private LocalDateTime dataAtualizacao;

    @Schema(description = "Respostas ao feedbacks")
    private List<RespostaResDto> retorno;

    public FeedbackResDto(FeedbackEntity feedbackEntity) {
        BeanUtils.copyProperties(feedbackEntity, this);
    }

    public FeedbackResDto(FeedbackEntity feedbackEntity, List<RespostaEntity> repostaEntity) {
        this(feedbackEntity);
        this.retorno = repostaEntity.stream().map(RespostaResDto::new).toList();
    }
}
