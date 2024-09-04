package com.fullstack.feedbacks.services;

import com.fullstack.feedbacks.entities.RespostaEntity;

import java.util.List;

public interface RespostaService {
    RespostaEntity criar(RespostaEntity respostaEntity);

    List<RespostaEntity> buscarPorSugestaoId(Long sugestaoId);
}
