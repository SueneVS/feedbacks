package com.fullstack.feedbacks.services;


import com.fullstack.feedbacks.entities.FeedbackEntity;


import java.util.List;

public interface FeedbackService {
    FeedbackEntity salvar(FeedbackEntity feedbackEntity);

    List<FeedbackEntity> buscarTodos(String titulo);

    FeedbackEntity buscarPorId(Long id);
}
