package com.web.lawingmachine.app.exam.mapper;


import com.web.lawingmachine.app.exam.vo.QuizDtlInfoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuizUserAnsDtlMapper {

    int mergeUserAnswerDtl(QuizDtlInfoVO quizDtlInfoVO);

    int insertUserAnswerDtl(QuizDtlInfoVO quizDtlInfoVO);

    int updateUserAnswerDtl(QuizDtlInfoVO quizDtlInfoVO);
}
