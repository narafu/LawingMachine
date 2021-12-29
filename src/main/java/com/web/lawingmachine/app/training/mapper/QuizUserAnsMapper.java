package com.web.lawingmachine.app.training.mapper;

import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuizUserAnsMapper {

    int mergeUserAnswer(QuizMstrInfoVO param);

    List<QuizMstrInfoVO> selectQuizResultRatioList(QuizMstrInfoVO param);

//    List<QuizMstrInfoVO> getQuizCntList(QuizMstrInfoVO param);
//
//    List<Map<String, String>> selectQuizUsrAnsSeqAll(UserInfoVO userInfoVO);
//
//
//    int mergeUserAnswer(Map<String, String> map);

}
