package com.web.lawingmachine.app.exam.dto;

import lombok.Data;

@Data
public class QuizSubjectUserDto {

    private String subjectTypeNm;
    private String subjectTypeCd;
    private int quizTotalCnt;
    private char duplicateYn;
}
