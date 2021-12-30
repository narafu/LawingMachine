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

//    @Override
//    public Map<String, Object> selectQuizResultRatioList(QuizMstrInfoVO param) {
//
//        QuizMstrInfoVO quizMstrInfoVO = param;
//        List<Map<String, String>> mapList = quizUserAnsMapper.selectQuizResultChartAxisList(param);
//
//        Map<String, Object> resultMap = new HashMap<>();
//        List<String> axisXList = new ArrayList<>();
//        List<String> axisYList = new ArrayList<>();
//        List<List<Integer>> dataList = new ArrayList<>();
//
//        for(Map<String, String> map : mapList) {
//            axisXList.add(String.valueOf(map.get("QUIZ_TOTAL_CNT")));
//            axisYList.add(map.get("SUBJECT_TYPE_NM"));
//            quizMstrInfoVO.setSchSubjectTypeCd(map.get("SUBJECT_TYPE_CD"));
//            List<Integer> list = quizUserAnsMapper.selectQuizResultRatioList(quizMstrInfoVO);
//            dataList.add(list);
//        }
//
//        resultMap.put("axisXList", axisXList);
//        resultMap.put("axisYList", axisYList);
//        resultMap.put("dataList", dataList);
//
//        return resultMap;
//    }

}
