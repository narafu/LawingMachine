package com.web.lawingmachine.app.training.service.impl;

import com.web.lawingmachine.app.training.mapper.QuizDtlInfoMapper;
import com.web.lawingmachine.app.training.mapper.QuizMstrInfoMapper;
import com.web.lawingmachine.app.training.mapper.QuizUserAnsDtlMapper;
import com.web.lawingmachine.app.training.mapper.QuizUserAnsMapper;
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


//
//    @Override
//    public List<QuizMstrInfoVO> getQuizCntList(QuizMstrInfoVO param) {
//        return quizUserAnsMapper.getQuizCntList(param);
//    }
//
//    @Override
//    public int mergeSelectQuizAll(UserInfoVO userInfoVO) {
//        List<Map<String, String>> mapList = quizUserAnsMapper.selectQuizUsrAnsSeqAll(userInfoVO);
//        for (Map<String, String> map : mapList) {
//            map.put("userId", userInfoVO.getUserId());
//            quizUserAnsMapper.mergeUserAnswer(map);
//        }
//        return mapList.size();
//    }
//
//    @Override
//    public List<Map<String, String>> selectQuizNoList(QuizMstrInfoVO quizMstrInfoVO) {
//        return quizMstrInfoMapper.selectQuizNoList(quizMstrInfoVO);
//    }
//
//    @Override
//    public int regQuiz(QuizMstrInfoVO param) {
//        int resultCnt = quizMstrInfoMapper.insertQuizMstrInfo(param);
//        if (resultCnt > 0) {
//            for (QuizDtlInfoVO quizDtlInfoVO : param.getQuizDtlInfoList()) {
//                quizDtlInfoVO.setQuizMstrInfoSeq(param.getQuizMstrInfoSeq());
//                quizDtlInfoMapper.insertQuizDtlInfo(quizDtlInfoVO);
//            }
//        }
//        return resultCnt;
//    }
//
//    @Override
//    public int editQuiz(QuizMstrInfoVO param) {
//        int resultCnt = quizMstrInfoMapper.updateQuizMstrInfo(param);
//        if (resultCnt > 0) {
//            for (QuizDtlInfoVO quizDtlInfoVO : param.getQuizDtlInfoList()) {
//                quizDtlInfoMapper.updateQuizDtlInfo(quizDtlInfoVO);
//            }
//        }
//        return resultCnt;
//    }
//
//    @Override
//    public int delQuiz(QuizMstrInfoVO param) {
//        return quizMstrInfoMapper.delQuiz(param);
//    }
//
//    @Override
//    public QuizMstrInfoVO getQuizFormInfo(QuizMstrInfoVO param) {
//
//        QuizMstrInfoVO quizMstrInfoVO = quizMstrInfoMapper.getQuizFormInfo(param);
//        if (quizMstrInfoVO == null) {
//            quizMstrInfoVO = param;
//        }
//
//        int quizTotalCnt = quizMstrInfoMapper.getQuizTotalCnt(param);
//        quizMstrInfoVO.setQuizTotalCnt(quizTotalCnt);
//
//        List<QuizDtlInfoVO> quizDtlInfoList = quizDtlInfoMapper.selectQuizMstrFormDtlList(param);
//        if (quizDtlInfoList.isEmpty()) {
//            quizDtlInfoList = new ArrayList<QuizDtlInfoVO>();
//            quizDtlInfoList.add(new QuizDtlInfoVO());
//            quizDtlInfoList.add(new QuizDtlInfoVO());
//            quizDtlInfoList.add(new QuizDtlInfoVO());
//            quizDtlInfoList.add(new QuizDtlInfoVO());
//            quizDtlInfoList.add(new QuizDtlInfoVO());
//        }
//
//        quizMstrInfoVO.setQuizDtlInfoList(quizDtlInfoList);
//        return quizMstrInfoVO;
//    }

}
