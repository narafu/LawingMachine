package com.web.lawingmachine.app.exam.dto;

import lombok.Data;

@Data
public class QuizResultRatioDto {
    private Integer quizMstrInfoSeq;
    private Integer quizUserAnsSeq;
    private String subjectTypeCd;
    private String examGrpCd;
    private Integer examYear;
    private int examNo;
    private String content;
    private String answer;
    private String cmntr;
    private int quizNo;
    private String userAnswer;
    private String answerChk;
    private String userChkImprt;
    private String userChkCnfsd;
    private float tempQuizTrueCount;
    private float tempQuizTotalCount;
    private float quizTrueRatio;
}
