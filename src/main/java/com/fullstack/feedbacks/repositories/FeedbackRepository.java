package com.fullstack.feedbacks.repositories;

import com.fullstack.feedbacks.entities.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {
    List<FeedbackEntity> findByOrderByDataAtualizacaoDesc();
    List<FeedbackEntity> findByTituloContainingIgnoreCaseOrderByDataAtualizacaoDesc(String titulo);
}
