package com.web.lawingmachine.app.training.service;

import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;

import java.util.List;
import java.util.Map;


public interface QuizService {

    List<QuizMstrInfoVO> selectAjaxQuizNavList(QuizMstrInfoVO quizMstrInfoVO);

    List<Map<String, String>> selectQuizSubjectList();

    QuizMstrInfoVO getAjaxQuizMstrInfo(QuizMstrInfoVO param);

//    List<QuizMstrInfoVO> getQuizCntList(QuizMstrInfoVO param);
//
//    int updateUserAnswer(QuizMstrInfoVO param);
//
//    int mergeSelectQuizAll(UserInfoVO userInfoVO);
//
//    int regQuiz(QuizMstrInfoVO param);
//
//    int editQuiz(QuizMstrInfoVO param);
//
//    List<Map<String, String>> selectQuizNoList(QuizMstrInfoVO quizMstrInfoVO);
//
//    int delQuiz(QuizMstrInfoVO param);
//
//    QuizMstrInfoVO getQuizFormInfo(QuizMstrInfoVO quizMstrInfoVO);

}
