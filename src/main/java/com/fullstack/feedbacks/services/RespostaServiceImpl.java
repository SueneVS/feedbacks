package com.fullstack.feedbacks.services;

import com.fullstack.feedbacks.entities.RespostaEntity;
import com.fullstack.feedbacks.repositories.RespostaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RespostaServiceImpl implements RespostaService{
    private final RespostaRepository respostaRepository;

    @Override
    public RespostaEntity criar(RespostaEntity respostaEntity) {
        return respostaRepository.save(respostaEntity);
    }

    @Override
    public List<RespostaEntity> buscarPorSugestaoId(Long feedbackId) {
        return respostaRepository.findByFeedbackIdOrderByDataEnvioDesc(feedbackId);
    }
}
