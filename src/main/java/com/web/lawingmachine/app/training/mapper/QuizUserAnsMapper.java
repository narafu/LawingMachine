package com.web.lawingmachine.app.training.mapper;

import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuizUserAnsMapper {

    int mergeUserAnswer(QuizMstrInfoVO param);

    List<QuizMstrInfoVO> selectQuizResultRatioList(QuizMstrInfoVO param);

    List<Map<String, String>> selectSubjectList(QuizMstrInfoVO param);

}
