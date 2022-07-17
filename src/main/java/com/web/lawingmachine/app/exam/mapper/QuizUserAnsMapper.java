package com.web.lawingmachine.app.exam.mapper;

import com.web.lawingmachine.app.exam.dto.QuizResultRatioDto;
import com.web.lawingmachine.app.exam.vo.QuizMstrInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuizUserAnsMapper {

    int mergeUserAnswer(QuizMstrInfoVO param);

    List<QuizResultRatioDto> selectQuizResultRatioList(QuizMstrInfoVO param);

    List<Map<String, String>> selectSubjectList(QuizMstrInfoVO param);

    int insertUserAnswer(QuizMstrInfoVO param);

    int updateUserAnswer(QuizMstrInfoVO param);
}
