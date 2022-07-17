package com.web.lawingmachine.app.exam.entity;

import com.web.lawingmachine.app.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Table(name = "QUIZ_USER_ANS")
@Data
@Entity
public class QuizUserAnsEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int quizUserAnsSeq;

    private int quizMstrInfoSeq;

    private String userId;

    private String userAnswer;

    @Column(length = 1)
    private char answerChk = 'X';

    private String userChkImprt;

    private String userChkCnfsd;

    private int takeRev;
}
