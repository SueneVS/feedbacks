package com.fullstack.feedbacks.entities;

import com.fullstack.feedbacks.dtos.FeedbackReqDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "feedbacks")
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 512)
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataEnvio = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataAtualizacao = LocalDateTime.now();

    public FeedbackEntity(FeedbackReqDto feedbackReqDto) {
        BeanUtils.copyProperties(feedbackReqDto, this);
    }
}
