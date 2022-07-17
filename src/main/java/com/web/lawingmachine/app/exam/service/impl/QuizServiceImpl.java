package com.web.lawingmachine.app.exam.service.impl;

import com.web.lawingmachine.app.exam.dto.QuizResultRatioDto;
import com.web.lawingmachine.app.exam.dto.QuizSubjectDto;
import com.web.lawingmachine.app.exam.dto.QuizSubjectUserDto;
import com.web.lawingmachine.app.exam.mapper.*;
import com.web.lawingmachine.app.exam.service.QuizService;
import com.web.lawingmachine.app.exam.vo.QuizDtlInfoVO;
import com.web.lawingmachine.app.exam.vo.QuizMstrInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

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
    public List<QuizSubjectDto> selectQuizSubjectList() {
        // 공통코드(과목코드)
        String grpCd = "002";
        return quizMstrInfoMapper.selectQuizSubjectList(grpCd);
    }

    @Override
    public QuizMstrInfoVO getAjaxQuizMstrInfo(QuizMstrInfoVO param) {

        QuizMstrInfoVO quizMstrInfoVO = quizMstrInfoMapper.getQuizMstrInfo(param);

        // 전체 문제 갯수
        int quizTotalCnt = quizMstrInfoMapper.getQuizTotalCnt(param);
        quizMstrInfoVO.setQuizTotalCnt(quizTotalCnt);

        // 푼 문제 갯수
        int quizEndCnt = quizMstrInfoMapper.getQuizEndCnt(param);
        quizMstrInfoVO.setQuizEndCnt(quizEndCnt);

        List<QuizDtlInfoVO> quizDtlList = quizDtlInfoMapper.selectQuizDtlList(quizMstrInfoVO);
        quizMstrInfoVO.setQuizDtlList(quizDtlList);

        return quizMstrInfoVO;
    }

    @Override
    public int saveUserAnswer(QuizMstrInfoVO param) {

        int resultCnt = 0;

        if (StringUtils.isEmpty(param.getQuizUserAnsSeq())) {
            resultCnt = quizUserAnsMapper.insertUserAnswer(param);
        } else {
            resultCnt = quizUserAnsMapper.updateUserAnswer(param);
        }
//        resultCnt = quizUserAnsMapper.mergeUserAnswer(param);

        if (resultCnt > 0) {
            List<QuizDtlInfoVO> quizDtlList = param.getQuizDtlList();
            for (QuizDtlInfoVO quizDtlInfoVO : quizDtlList) {
                quizDtlInfoVO.setQuizUserAnsSeq(param.getQuizUserAnsSeq());
//                quizUserAnsDtlMapper.mergeUserAnswerDtl(quizDtlInfoVO);
                if (StringUtils.isEmpty(quizDtlInfoVO.getQuizUserAnsDtlSeq())) {
                    quizUserAnsDtlMapper.insertUserAnswerDtl(quizDtlInfoVO);
                } else {
                    quizUserAnsDtlMapper.updateUserAnswerDtl(quizDtlInfoVO);
                }
            }
        }
        return resultCnt;
    }

    @Override
    public List<QuizDtlInfoVO> selectQuizDtlList(QuizMstrInfoVO param) {
        return quizDtlInfoMapper.selectQuizDtlList(param);
    }

    @Override
    public List<QuizResultRatioDto> selectQuizResultRatioList(QuizMstrInfoVO param) {
        List<QuizResultRatioDto> quizMstrInfoVOList = quizUserAnsMapper.selectQuizResultRatioList(param);
        for (QuizResultRatioDto quizResultRatioDto : quizMstrInfoVOList) {
            float trueRatio = quizResultRatioDto.getTempQuizTrueCount() / quizResultRatioDto.getTempQuizTotalCount() * 100;
            String formatTrueRatio = String.format("%.2f", trueRatio);
            quizResultRatioDto.setQuizTrueRatio(Float.valueOf(formatTrueRatio));
        }
        return quizMstrInfoVOList;
    }

    @Override
    public List<Map<String, String>> selectSubjectList(QuizMstrInfoVO param) {
        return quizUserAnsMapper.selectSubjectList(param);
    }

    @Override
    public List<QuizSubjectUserDto> selectQuizSubjectUserList(String userId) {
        return quizMstrInfoMapper.selectQuizSubjectUserList(userId);
    }

    @Override
    public int mergeQuizResultInfo(QuizMstrInfoVO param) {
        int resultCnt = 0;
        QuizMstrInfoVO quizMstrInfoVO = quizResultInfoMapper.getQuizResultInfoSeq(param);
        if (quizMstrInfoVO == null) {
            resultCnt = quizResultInfoMapper.insertQuizResultInfo(param);
        } else {
            resultCnt = quizResultInfoMapper.updateQuizResultInfo(quizMstrInfoVO);
        }
        return resultCnt;
    }

    @Override
    public List<Map<String, Object>> getQuizResultUserSubjectCnt(QuizMstrInfoVO param) {
        return quizResultInfoMapper.getQuizResultUserSubjectCnt(param);
    }

    @Override
    public List<Map<String, Object>> selectQuizResultList(QuizMstrInfoVO param) {
        param.setOffset(param.getOffset() * 20);
        param.setPageSize(20);
        return quizResultInfoMapper.selectQuizResultList(param);
    }

    @Override
    public int getQuizResultListCnt(QuizMstrInfoVO param) {
        return quizResultInfoMapper.getQuizResultListCnt(param);
    }

    @Override
    public List<Map<String, Object>> getQuizResultInfo(String userId) {
        return quizResultInfoMapper.getQuizResultInfo(userId);
    }

    @Override
    public int getMemberTotalCnt(QuizMstrInfoVO param) {
        return quizResultInfoMapper.getMemberTotalCnt(param);
    }
}
