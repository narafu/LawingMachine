package com.web.lawingmachine.app.exam.entity;

import com.web.lawingmachine.app.common.entity.BaseEntity;
import lombok.Data;

import jakarta.persistence.*;

@Table(name = "QUIZ_USER_ANS_DTL")
@Data
@Entity
public class QuizUserAnsDtlEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer quizUserAnsDtlSeq;

    private Integer quizUserAnsSeq;

    private Integer quizDtlInfoSeq;

    @Column(length = 1)
    private char eraseYn;

}
