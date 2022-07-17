package com.web.lawingmachine.app.exam.vo;

import com.web.lawingmachine.app.common.vo.BaseVO;
import lombok.Data;

@Data
public class QuizDtlInfoVO extends BaseVO {

    private String quizMstrInfoSeq;
    private String quizDtlInfoSeq;
    private String quizUserAnsSeq;
    private String quizUserAnsDtlSeq;
    private int srtNo;
    private String content;
    private String cmntr;
    private String eraseYn = "N";
}
