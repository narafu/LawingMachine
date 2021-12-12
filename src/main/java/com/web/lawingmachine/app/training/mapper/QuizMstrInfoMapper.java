package com.web.lawingmachine.app.training.mapper;

import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuizMstrInfoMapper {

    List<QuizMstrInfoVO> selectAjaxQuizNavList(QuizMstrInfoVO quizMstrInfoVO);

    List<Map<String, String>> selectQuizSubjectList();

    int getQuizTotalCnt(QuizMstrInfoVO param);

    int getQuizEndCnt(QuizMstrInfoVO param);

    QuizMstrInfoVO getQuizMstrInfo(QuizMstrInfoVO param);
//
//    List<Map<String, String>> selectQuizNoList(QuizMstrInfoVO quizMstrInfoVO);
//
//    int insertQuizMstrInfo(QuizMstrInfoVO param);
//
//    int updateQuizMstrInfo(QuizMstrInfoVO param);
//
//    int delQuiz(QuizMstrInfoVO param);
//
//    QuizMstrInfoVO getQuizFormInfo(QuizMstrInfoVO param);
//
//    QuizMstrInfoVO getFstQuizInfo();


}
