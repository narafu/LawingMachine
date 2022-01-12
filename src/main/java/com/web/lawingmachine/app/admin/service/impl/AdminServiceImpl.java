package com.web.lawingmachine.app.admin.service.impl;

import com.web.lawingmachine.app.admin.service.AdminService;
import com.web.lawingmachine.app.training.mapper.QuizDtlInfoMapper;
import com.web.lawingmachine.app.training.mapper.QuizMstrInfoMapper;
import com.web.lawingmachine.app.training.vo.QuizDtlInfoVO;
import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;
import com.web.lawingmachine.app.user.mapper.UserInfoMapper;
import com.web.lawingmachine.app.user.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private QuizMstrInfoMapper quizMstrInfoMapper;

	@Autowired
	private QuizDtlInfoMapper quizDtlInfoMapper;

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public List<QuizMstrInfoVO> selectQuizList(QuizMstrInfoVO quizMstrInfoVO) {
		quizMstrInfoVO.setOffset(quizMstrInfoVO.getOffset() * 10);
		return quizMstrInfoMapper.selectQuizList(quizMstrInfoVO);
	}

	@Override
	public int getQuizListCnt(QuizMstrInfoVO quizMstrInfoVO) {
		return quizMstrInfoMapper.getQuizListCnt(quizMstrInfoVO);
	}

	@Override
	public QuizMstrInfoVO getQuizInfo(QuizMstrInfoVO quizMstrInfoVO) {
		return quizMstrInfoMapper.getQuizInfo(quizMstrInfoVO);
	}

	@Override
	public int insertQuizMstrInfo(QuizMstrInfoVO param) {
		int resultCnt = quizMstrInfoMapper.insertQuizMstrInfo(param);
		if (resultCnt > 0) {
			for (QuizDtlInfoVO quizDtlInfoVO : param.getQuizDtlList()) {
				quizDtlInfoMapper.insertQuizDtlInfo(quizDtlInfoVO);
			}
		}
		return resultCnt;
	}

	@Override
	public int updateQuizMstrInfo(QuizMstrInfoVO param) {
		int resultCnt = quizMstrInfoMapper.updateQuizMstrInfo(param);
		if (resultCnt > 0) {
			for (QuizDtlInfoVO quizDtlInfoVO : param.getQuizDtlList()) {
				quizDtlInfoMapper.updateQuizDtlInfo(quizDtlInfoVO);
			}
		}
		return resultCnt;
	}

	@Override
	public int delQuizMstrInfo(QuizMstrInfoVO param) {
		int resultCnt = quizMstrInfoMapper.delQuizMstrInfo(param);
		if (resultCnt > 0) {
			for (QuizDtlInfoVO quizDtlInfoVO : param.getQuizDtlList()) {
				quizDtlInfoMapper.delQuizDtlInfo(quizDtlInfoVO);
			}
		}
		return resultCnt;
	}

	@Override
	public List<UserInfoVO> selectApprovalList(UserInfoVO param) {
		param.setOffset(param.getOffset() * 10);
		return userInfoMapper.selectApprovalList(param);
	}

	@Override
	public int getApprovalListCnt(UserInfoVO param) {
		return userInfoMapper.getApprovalListCnt(param);
	}

	@Override
	public int updateMembershipCd(String userId, String membershipCd) {
		return userInfoMapper.updateMembershipCd(userId, membershipCd);
	}
}
