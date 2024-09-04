package com.fullstack.feedbacks.repositories;

import com.fullstack.feedbacks.entities.RespostaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespostaRepository  extends JpaRepository<RespostaEntity, Long> {
    List<RespostaEntity> findByFeedbackIdOrderByDataEnvioDesc(Long feedbackId);
}
