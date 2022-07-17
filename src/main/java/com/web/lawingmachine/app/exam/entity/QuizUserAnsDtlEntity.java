package com.web.lawingmachine.app.exam.entity;

import com.web.lawingmachine.app.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Table(name = "QUIZ_USER_ANS_DTL")
@Data
@Entity
public class QuizUserAnsDtlEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int quizUserAnsDtlSeq;

    private int quizUserAnsSeq;

    private int quizDtlInfoSeq;

    @Column(length = 1)
    private char eraseYn;

}
