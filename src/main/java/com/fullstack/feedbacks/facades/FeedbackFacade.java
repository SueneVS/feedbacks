package com.fullstack.feedbacks.facades;

import com.fullstack.feedbacks.dtos.FeedbackReqDto;
import com.fullstack.feedbacks.dtos.FeedbackResDto;
import com.fullstack.feedbacks.dtos.RespostaReqDto;
import com.fullstack.feedbacks.dtos.RespostaResDto;

import java.util.List;

public interface FeedbackFacade {
    FeedbackResDto criar(FeedbackReqDto feedbackReqDtodto);

    List<FeedbackResDto> buscarTodos(String titulo);

    FeedbackResDto buscarPorId(Long id);

    RespostaResDto criarResposta(Long id, RespostaReqDto respostaReq);
}
