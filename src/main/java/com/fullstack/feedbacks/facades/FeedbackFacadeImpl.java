package com.fullstack.feedbacks.facades;

import com.fullstack.feedbacks.dtos.FeedbackReqDto;
import com.fullstack.feedbacks.dtos.FeedbackResDto;
import com.fullstack.feedbacks.dtos.RespostaReqDto;
import com.fullstack.feedbacks.dtos.RespostaResDto;
import com.fullstack.feedbacks.entities.FeedbackEntity;
import com.fullstack.feedbacks.entities.RespostaEntity;
import com.fullstack.feedbacks.services.FeedbackService;
import com.fullstack.feedbacks.services.RespostaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedbackFacadeImpl implements FeedbackFacade {
    private final FeedbackService feedbackService;
    private final RespostaService respostaService;

    @Override
    public FeedbackResDto criar(FeedbackReqDto feedbackReqDto) {
        log.info("Criando feedback {}", feedbackReqDto.getTitulo());
        FeedbackEntity feedbackEntity = new FeedbackEntity(feedbackReqDto);

        log.info("Salvando feedback {}", feedbackReqDto.getTitulo());
        feedbackService.salvar(feedbackEntity);

        log.info("Feedback salvo {} - {}", feedbackEntity.getId(), feedbackEntity.getTitulo());
        return new FeedbackResDto(feedbackEntity);
    }

    @Override
    public List<FeedbackResDto> buscarTodos(String titulo) {
        log.info("Buscando todos os feedbacks");
        return feedbackService.buscarTodos(titulo).stream().map(FeedbackResDto::new).toList();
    }

    @Override
    public FeedbackResDto buscarPorId(Long id) {
        log.info("Buscando feedback por ID: {}", id);
        FeedbackEntity feedbackEntity = feedbackService.buscarPorId(id);
        List<RespostaEntity> retornos = respostaService.buscarPorSugestaoId(id);

        log.info("Feedback encontrado com ID: {}", id);
        return new FeedbackResDto(feedbackEntity, retornos);
    }

    @Override
    public RespostaResDto criarResposta(Long id, RespostaReqDto respostaReqDto) {

        log.info("Criando resposta {}", respostaReqDto);
        FeedbackEntity feedbackEntity = feedbackService.buscarPorId(id);

        RespostaEntity respostaEntity = new RespostaEntity(respostaReqDto);
        respostaEntity.setFeedback(feedbackEntity);
        respostaService.criar(respostaEntity);

        log.info("Salvando resposta {}", respostaReqDto);
        feedbackEntity.setDataAtualizacao(LocalDateTime.now());
        feedbackService.salvar(feedbackEntity);


        log.info("Resposta salvo {} - {}", feedbackEntity.getId(), feedbackEntity.getTitulo());
        return new RespostaResDto(respostaEntity);
    }
}
