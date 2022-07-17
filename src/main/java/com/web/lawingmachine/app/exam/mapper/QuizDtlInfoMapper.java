package com.web.lawingmachine.app.exam.mapper;

import com.web.lawingmachine.app.exam.vo.QuizDtlInfoVO;
import com.web.lawingmachine.app.exam.vo.QuizMstrInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuizDtlInfoMapper {

    List<QuizDtlInfoVO> selectQuizDtlList(QuizMstrInfoVO param);

    int insertQuizDtlInfo(QuizDtlInfoVO quizDtlInfoVO);

    int updateQuizDtlInfo(QuizDtlInfoVO quizDtlInfoVO);

    void delQuizDtlInfo(QuizDtlInfoVO quizDtlInfoVO);
}
