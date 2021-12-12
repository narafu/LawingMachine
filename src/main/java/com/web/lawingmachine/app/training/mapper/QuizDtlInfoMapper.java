package com.web.lawingmachine.app.training.mapper;

import com.web.lawingmachine.app.training.vo.QuizDtlInfoVO;
import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuizDtlInfoMapper {

    List<QuizDtlInfoVO> selectQuizDtlList(QuizMstrInfoVO param);

    int insertQuizDtlInfo(QuizDtlInfoVO quizDtlInfoVO);

    int updateQuizDtlInfo(QuizDtlInfoVO quizDtlInfoVO);

    List<QuizDtlInfoVO> selectQuizMstrFormDtlList(QuizMstrInfoVO param);

}
