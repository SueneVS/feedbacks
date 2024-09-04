package com.fullstack.feedbacks.services;


import com.fullstack.feedbacks.entities.FeedbackEntity;
import com.fullstack.feedbacks.infra.exceptions.ExceptionNotFound;
import com.fullstack.feedbacks.repositories.FeedbackRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    @Override
    public FeedbackEntity salvar(FeedbackEntity feedbackEntity) {
        return feedbackRepository.save(feedbackEntity);
    }

    @Override
    public List<FeedbackEntity> buscarTodos(String titulo) {
        if (StringUtils.isBlank(titulo)) {
            titulo = "";
        }
        return feedbackRepository.findByTituloContainingIgnoreCaseOrderByDataAtualizacaoDesc(titulo);
    }

    @Override
    public FeedbackEntity buscarPorId(Long id) {
        return feedbackRepository.findById(id).orElseThrow(
                () -> new ExceptionNotFound("Feedback não encontrado com ID: " + id)
        );
    }
}
