package com.web.lawingmachine.app.training.vo;

import com.web.lawingmachine.app.common.vo.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class QuizMstrInfoVO extends BaseVO {

    private String quizMstrInfoSeq;
    private String quizUserAnsSeq;

    private String examGrpCd;
    private String examGrpNm;
    private String examYear;
    private int examNo;

    private String subjectTypeCd;
    private String subjectTypeNm;

    private int srtNo;

    private String content;
    private String answer;
    private String cmntr;

    private String userId;
    private String userAnswer;
    private String answerChk;
    private String userChkImprt;
    private String userChkCnfsd;
    private int takeRev;

    private int secSolving;
    private int minSolving;

    private int quizTotalCnt;
    private int quizEndCnt;
    private int quizTrueCnt;

    private List<QuizDtlInfoVO> quizDtlList;
}
