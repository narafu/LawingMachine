package com.web.lawingmachine.app.training.service;

import com.web.lawingmachine.app.training.vo.QuizDtlInfoVO;
import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;

import java.util.List;
import java.util.Map;


public interface QuizService {

    List<QuizMstrInfoVO> selectAjaxQuizNavList(QuizMstrInfoVO quizMstrInfoVO);

    List<Map<String, String>> selectQuizSubjectList();

    QuizMstrInfoVO getAjaxQuizMstrInfo(QuizMstrInfoVO param);

    int saveUserAnswer(QuizMstrInfoVO param);

    List<QuizDtlInfoVO> selectQuizDtlList(QuizMstrInfoVO param);
}
