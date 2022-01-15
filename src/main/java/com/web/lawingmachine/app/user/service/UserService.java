package com.web.lawingmachine.app.user.service;

import com.web.lawingmachine.app.user.vo.UserInfoVO;

public interface UserService {

	int insertUserInfo(UserInfoVO userInfoVO);

	UserInfoVO getUserInfo(String userId);

	int updateUserInfo(UserInfoVO userInfoVO);

	int updateLoginUserInfo(UserInfoVO userInfoVO);

	int uploadImage(String examTicketPath, String filename, String userId);

	int updateMembershipCd(UserInfoVO userInfoVO);
}
