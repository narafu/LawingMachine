package com.web.lawingmachine.app.user.mapper;

import com.web.lawingmachine.app.user.vo.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {

    int insertUserInfo(UserInfoVO userInfoVO);

    UserInfoVO getUserInfo(String userId);

}
