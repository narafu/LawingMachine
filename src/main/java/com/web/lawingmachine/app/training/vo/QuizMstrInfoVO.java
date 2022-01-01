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
    private String quizNo;

    private String content;
    private String answer;
    private String cmntr;

    private String userId;
    private String userAnswer;
    private String answerChk;
    private String userChkImprt;
    private String userChkCnfsd;
    private int takeRev;

    private int quizTotalCnt;
    private int quizEndCnt;
    private int quizTrueCnt;
    private float quizTrueRatio;

    private List<QuizDtlInfoVO> quizDtlList;

    private String schSubjectTypeCd;
    private String schUserChkImprt;
    private String schUserChkCnfsd;
    private String schAnswerChk;
    private String schQuizCntnt;
}
