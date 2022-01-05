package com.web.lawingmachine.app.training.service.impl;

import com.web.lawingmachine.app.training.mapper.*;
import com.web.lawingmachine.app.training.service.QuizService;
import com.web.lawingmachine.app.training.vo.QuizDtlInfoVO;
import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizMstrInfoMapper quizMstrInfoMapper;

    @Autowired
    private QuizDtlInfoMapper quizDtlInfoMapper;

    @Autowired
    private QuizUserAnsMapper quizUserAnsMapper;

    @Autowired
    private QuizUserAnsDtlMapper quizUserAnsDtlMapper;

    @Autowired
    private QuizResultInfoMapper quizResultInfoMapper;


    @Override
    public List<QuizMstrInfoVO> selectAjaxQuizNavList(QuizMstrInfoVO quizMstrInfoVO) {
        return quizMstrInfoMapper.selectAjaxQuizNavList(quizMstrInfoVO);
    }

    @Override
    public List<Map<String, String>> selectQuizSubjectList() {
        // 공통코드(과목코드)
        String grpCd = "002";
        return quizMstrInfoMapper.selectQuizSubjectList(grpCd);
    }

    @Override
    public QuizMstrInfoVO getAjaxQuizMstrInfo(QuizMstrInfoVO param) {

        QuizMstrInfoVO quizMstrInfoVO = quizMstrInfoMapper.getQuizMstrInfo(param);

        // 전체 문제 갯수
        int quizTotalCnt = quizMstrInfoMapper.getQuizTotalCnt(quizMstrInfoVO);
        quizMstrInfoVO.setQuizTotalCnt(quizTotalCnt);

        // 푼 문제 갯수
        int quizEndCnt = quizMstrInfoMapper.getQuizEndCnt(quizMstrInfoVO);
        quizMstrInfoVO.setQuizEndCnt(quizEndCnt);

        List<QuizDtlInfoVO> quizDtlList = quizDtlInfoMapper.selectQuizDtlList(quizMstrInfoVO);
        quizMstrInfoVO.setQuizDtlList(quizDtlList);

        return quizMstrInfoVO;
    }

    @Override
    public int saveUserAnswer(QuizMstrInfoVO param) {

        int resultCnt = quizUserAnsMapper.mergeUserAnswer(param);

        if (resultCnt > 0) {
            List<QuizDtlInfoVO> quizDtlList = param.getQuizDtlList();
            for (QuizDtlInfoVO quizDtlInfoVO : quizDtlList) {
                quizDtlInfoVO.setQuizUserAnsSeq(param.getQuizUserAnsSeq());
                quizUserAnsDtlMapper.mergeUserAnswerDtl(quizDtlInfoVO);
            }
        }
        return resultCnt;
    }

    @Override
    public List<QuizDtlInfoVO> selectQuizDtlList(QuizMstrInfoVO param) {
        return quizDtlInfoMapper.selectQuizDtlList(param);
    }

    @Override
    public List<QuizMstrInfoVO> selectQuizResultRatioList(QuizMstrInfoVO param) {
        return quizUserAnsMapper.selectQuizResultRatioList(param);
    }

    @Override
    public List<Map<String, String>> selectSubjectList(QuizMstrInfoVO param) {
        return quizUserAnsMapper.selectSubjectList(param);
    }

    @Override
    public List<QuizMstrInfoVO> selectQuizSubjectUserList(String userId) {
        return quizMstrInfoMapper.selectQuizSubjectUserList(userId);
    }

    @Override
    public int insertQuizResultInfo(QuizMstrInfoVO param) {
        return quizResultInfoMapper.insertQuizResultInfo(param);
    }

    @Override
    public int getQuizResultUserSubjectCnt(QuizMstrInfoVO param) {
        return quizResultInfoMapper.getQuizResultUserSubjectCnt(param);
    }

    @Override
    public List<Map<String, String>> selectQuizResultList(QuizMstrInfoVO param) {
        return quizResultInfoMapper.selectQuizResultList(param);
    }
}
