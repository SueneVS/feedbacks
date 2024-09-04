package com.fullstack.feedbacks.entities;

import com.fullstack.feedbacks.dtos.RespostaReqDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;


import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "respostas")
public class RespostaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 512)
    private String retorno;

    @Column(nullable = false)
    private LocalDateTime dataEnvio = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name="feedback_id", nullable=false)
    private FeedbackEntity feedback;

    public RespostaEntity(RespostaReqDto respostaReqDto) {
        BeanUtils.copyProperties(respostaReqDto, this);
    }

}
