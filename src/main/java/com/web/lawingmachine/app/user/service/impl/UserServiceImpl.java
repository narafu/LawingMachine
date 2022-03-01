package com.web.lawingmachine.app.user.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.web.lawingmachine.app.security.SessionUser;
import com.web.lawingmachine.app.user.mapper.UserInfoMapper;
import com.web.lawingmachine.app.user.service.UserService;
import com.web.lawingmachine.app.user.vo.UserInfoVO;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public int insertUserInfo(UserInfoVO param) {
		String userId = param.getUserId();
		String[] nickname = userId.split("@");
		param.setNickname(nickname[0]);
		return userInfoMapper.insertUserInfo(param);
	}

	@Override
	public UserInfoVO getUserInfo(String userId) {
		return userInfoMapper.getUserInfo(userId);
	}

	@Override
	public int updateUserInfo(UserInfoVO userInfoVO) {
		return userInfoMapper.updateUserInfo(userInfoVO);
	}

	@Override
	public int updateLoginUserInfo(UserInfoVO userInfoVO) {
		return userInfoMapper.updateLoginUserInfo(userInfoVO);
	}

	@Override
	public int uploadImage(String examTicketPath, String filename, String userId) {
		return userInfoMapper.uploadImage(examTicketPath, filename, userId);
	}

	@Override
	public int updateMembershipCd(UserInfoVO userInfoVO) {
		return userInfoMapper.updateMembershipCd(userInfoVO);
	}

	/**
	 * Spring Security 필수 메소드 구현
	 */
	@Override
	public UserDetails loadUserByUsername(String param) throws UsernameNotFoundException {
		
		int userInfoSeq = userInfoMapper.getGuestInfoSeq();
		String userId = param + userInfoSeq;

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = encoder.encode(param);
		
		UserInfoVO userInfoVO = new UserInfoVO();
		userInfoVO.setUserId(userId + "@GUEST.COM");
		userInfoVO.setPassword(password);
		userInfoVO.setUserNm(userId);
		userInfoVO.setEmail(userId + "@GUEST.COM");
		userInfoVO.setNickname(userId);
		userInfoVO.setMobile("010-1234-5678");
		userInfoVO.setMembershipCd("99");
		userInfoVO.setRole("ROLE_ADMIN");
		userInfoMapper.insertUserInfo(userInfoVO);
		
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		httpSession = req.getSession();
		httpSession.setAttribute("sessionUser", new SessionUser(userInfoMapper.getUserInfoBySeq(userInfoSeq)));
		return userInfoVO;
	}

}
