package com.web.lawingmachine.app.exam.mapper;

import com.web.lawingmachine.app.exam.vo.QuizMstrInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuizResultInfoMapper {

    int insertQuizResultInfo(QuizMstrInfoVO param);

    List<Map<String, Object>> getQuizResultUserSubjectCnt(QuizMstrInfoVO param);

    List<Map<String, Object>> selectQuizResultList(QuizMstrInfoVO param);

    List<Map<String, Object>> getQuizResultInfo(String userId);

	int getMemberTotalCnt(QuizMstrInfoVO param);

    int updateQuizResultInfo(QuizMstrInfoVO param);

    QuizMstrInfoVO getQuizResultInfoSeq(QuizMstrInfoVO param);

    int getQuizResultListCnt(QuizMstrInfoVO param);
}
