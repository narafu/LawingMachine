package com.web.lawingmachine.app.exam.entity;

import com.web.lawingmachine.app.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Table(name = "QUIZ_RESULT_INFO")
@Data
@Entity
public class QuizResultInfoEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int quizResultInfoSeq;

    @Column(length = 4)
    private int examYear;

    @Column(length = 10)
    private String examGrpCd;

    private int examNo;

    @Column(length = 10)
    private String subjectTypeCd;

    private String userId;

    private int resultCnt;
}
