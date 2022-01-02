package com.web.lawingmachine.app.training.mapper;

import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuizResultInfoMapper {

    int insertQuizResultInfo(QuizMstrInfoVO param);

    int getQuizResultUserSubjectCnt(QuizMstrInfoVO param);
}
