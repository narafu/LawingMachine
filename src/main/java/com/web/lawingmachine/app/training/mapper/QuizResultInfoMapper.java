package com.web.lawingmachine.app.training.mapper;

import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuizResultInfoMapper {

    int insertQuizResultInfo(QuizMstrInfoVO param);

    int getQuizResultUserSubjectCnt(QuizMstrInfoVO param);

    List<Map<String, String>> selectQuizResultList(QuizMstrInfoVO param);
}
