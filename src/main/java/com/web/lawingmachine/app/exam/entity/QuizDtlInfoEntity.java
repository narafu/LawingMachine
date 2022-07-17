package com.web.lawingmachine.app.exam.entity;

import com.web.lawingmachine.app.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Table(name = "QUIZ_DTL_INFO")
@Data
@Entity
public class QuizDtlInfoEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int quizDtlInfoSeq;

    private int quizMstrInfoSeq;

    private int srtNo;

    private String content;

    private String cmntr;

}
