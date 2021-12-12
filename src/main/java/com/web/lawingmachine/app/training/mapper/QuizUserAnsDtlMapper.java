package com.web.lawingmachine.app.training.mapper;


import com.web.lawingmachine.app.training.vo.QuizDtlInfoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuizUserAnsDtlMapper {

    int mergeUserAnswerDtl(QuizDtlInfoVO quizDtlInfoVO);

}
