package com.web.lawingmachine.app.exam.vo;

import com.web.lawingmachine.app.common.vo.BaseVO;
import lombok.Data;

@Data
public class QuizDtlInfoVO extends BaseVO {

    private Integer quizMstrInfoSeq;
    private Integer quizDtlInfoSeq;
    private Integer quizUserAnsSeq;
    private Integer quizUserAnsDtlSeq;
    private int srtNo;
    private String content;
    private String cmntr;
    private String eraseYn = "N";
}
