package com.web.lawingmachine.app.exam.mapper;

import com.web.lawingmachine.app.exam.dto.QuizSubjectDto;
import com.web.lawingmachine.app.exam.dto.QuizSubjectUserDto;
import com.web.lawingmachine.app.exam.vo.QuizMstrInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuizMstrInfoMapper {

    List<QuizMstrInfoVO> selectAjaxQuizNavList(QuizMstrInfoVO quizMstrInfoVO);

    List<QuizSubjectDto> selectQuizSubjectList(String grpCd);

    int getQuizTotalCnt(QuizMstrInfoVO param);

    int getQuizEndCnt(QuizMstrInfoVO param);

    QuizMstrInfoVO getQuizMstrInfo(QuizMstrInfoVO param);

    List<QuizMstrInfoVO> selectQuizList(QuizMstrInfoVO quizMstrInfoVO);

    int getQuizListCnt(QuizMstrInfoVO quizMstrInfoVO);

    QuizMstrInfoVO getQuizInfo(QuizMstrInfoVO quizMstrInfoVO);

    int insertQuizMstrInfo(QuizMstrInfoVO quizMstrInfoVO);

    List<QuizSubjectUserDto> selectQuizSubjectUserList(String userId);

    int updateQuizMstrInfo(QuizMstrInfoVO param);

    int delQuizMstrInfo(QuizMstrInfoVO param);
}
