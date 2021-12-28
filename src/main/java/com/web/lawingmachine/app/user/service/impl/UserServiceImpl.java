package com.web.lawingmachine.app.user.service.impl;

import com.web.lawingmachine.app.user.mapper.UserInfoMapper;
import com.web.lawingmachine.app.user.service.UserService;
import com.web.lawingmachine.app.user.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int insertUserInfo(UserInfoVO param) {
        String userId = param.getUserId();
        if (StringUtils.isEmpty(userId)) {
            String uuid = UUID.randomUUID().toString();
            param.setUserId(uuid);
            param.setUserNm("GUEST");
        } else {
            String[] userNm = userId.split("@");
            param.setUserNm(userNm[0]);
        }
        return userInfoMapper.insertUserInfo(param);
    }

    @Override
    public UserInfoVO getUserInfo(String userId) {
        return userInfoMapper.getUserInfo(userId);
    }

}
