package com.web.lawingmachine.app.exam.dto;

import lombok.Data;

@Data
public class QuizResultRatioDto {
    private int quizMstrInfoSeq;
    private int quizUserAnsSeq;
    private String subjectTypeCd;
    private String examGrpCd;
    private String examYear;
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
