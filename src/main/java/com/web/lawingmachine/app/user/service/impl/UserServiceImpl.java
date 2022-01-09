package com.web.lawingmachine.app.user.service.impl;

import com.web.lawingmachine.app.user.mapper.UserInfoMapper;
import com.web.lawingmachine.app.user.service.UserService;
import com.web.lawingmachine.app.user.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

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

}
