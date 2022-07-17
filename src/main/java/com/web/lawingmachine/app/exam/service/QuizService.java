package com.web.lawingmachine.app.exam.service;

import com.web.lawingmachine.app.exam.dto.QuizResultRatioDto;
import com.web.lawingmachine.app.exam.dto.QuizSubjectDto;
import com.web.lawingmachine.app.exam.dto.QuizSubjectUserDto;
import com.web.lawingmachine.app.exam.vo.QuizDtlInfoVO;
import com.web.lawingmachine.app.exam.vo.QuizMstrInfoVO;

import java.util.List;
import java.util.Map;


public interface QuizService {

    List<QuizMstrInfoVO> selectAjaxQuizNavList(QuizMstrInfoVO quizMstrInfoVO);

    List<QuizSubjectDto> selectQuizSubjectList();

    QuizMstrInfoVO getAjaxQuizMstrInfo(QuizMstrInfoVO param);

    int saveUserAnswer(QuizMstrInfoVO param);

    List<QuizDtlInfoVO> selectQuizDtlList(QuizMstrInfoVO param);

    List<QuizResultRatioDto> selectQuizResultRatioList(QuizMstrInfoVO param);

    List<Map<String, String>> selectSubjectList(QuizMstrInfoVO param);

    List<QuizSubjectUserDto> selectQuizSubjectUserList(String userId);

    List<Map<String, Object>> getQuizResultUserSubjectCnt(QuizMstrInfoVO param);

    List<Map<String, Object>> selectQuizResultList(QuizMstrInfoVO param);

    List<Map<String, Object>> getQuizResultInfo(String userId);

	int getMemberTotalCnt(QuizMstrInfoVO param);

    int mergeQuizResultInfo(QuizMstrInfoVO param);

    int getQuizResultListCnt(QuizMstrInfoVO param);
}
