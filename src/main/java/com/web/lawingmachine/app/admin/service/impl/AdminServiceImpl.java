package com.web.lawingmachine.app.admin.service.impl;

import com.web.lawingmachine.app.admin.service.AdminService;
import com.web.lawingmachine.app.training.mapper.QuizMstrInfoMapper;
import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private QuizMstrInfoMapper quizMstrInfoMapper;

    @Override
    public List<QuizMstrInfoVO> selectQuizList(QuizMstrInfoVO quizMstrInfoVO) {
        return quizMstrInfoMapper.selectQuizList(quizMstrInfoVO);
    }

    @Override
    public int getQuizListCnt(QuizMstrInfoVO quizMstrInfoVO) {
        return quizMstrInfoMapper.getQuizListCnt(quizMstrInfoVO);
    }

    @Override
    public QuizMstrInfoVO getQuizInfo(QuizMstrInfoVO quizMstrInfoVO) {
        return quizMstrInfoMapper.getQuizInfo(quizMstrInfoVO);
    }

    @Override
    public int insertQuizMstrInfo(QuizMstrInfoVO quizMstrInfoVO) {
        return quizMstrInfoMapper.insertQuizMstrInfo(quizMstrInfoVO);
    }
}
