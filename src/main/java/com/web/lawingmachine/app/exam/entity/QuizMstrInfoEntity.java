package com.web.lawingmachine.app.exam.entity;

import com.web.lawingmachine.app.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Table(name = "QUIZ_MSTR_INFO")
@Data
@Entity
public class QuizMstrInfoEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int quizMstrInfoSeq;

    @Column(length = 4)
    private int examYear;

    @Column(length = 10)
    private String examGrpCd;

    private int examNo;

    @Column(length = 10)
    private String subjectTypeCd;

    private int srtNo;

    private String content;

    private String answer;

    private String cmntr;
}
