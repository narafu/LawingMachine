package com.web.lawingmachine.app.admin.service;

import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;

import java.util.List;

public interface AdminService {

    List<QuizMstrInfoVO> selectQuizList(QuizMstrInfoVO quizMstrInfoVO);

    int getQuizListCnt(QuizMstrInfoVO quizMstrInfoVO);

    QuizMstrInfoVO getQuizInfo(QuizMstrInfoVO param);

    int insertQuizMstrInfo(QuizMstrInfoVO param);
}
