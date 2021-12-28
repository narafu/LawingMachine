package com.web.lawingmachine.app.user.service;

import com.web.lawingmachine.app.user.vo.UserInfoVO;

public interface UserService {

    int insertUserInfo(UserInfoVO userInfoVO);

    UserInfoVO getUserInfo(String userId);
}
