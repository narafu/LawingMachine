package com.web.lawingmachine.app.security;


import com.web.lawingmachine.app.user.vo.UserInfoVO;
import lombok.Data;

import java.io.Serializable;

@Data
public class SessionUser implements Serializable {

    private String userId;
    private String userNm;
    private String email;
    private String roleCd;

    public SessionUser(UserInfoVO userInfoVo) {
        this.userId = userInfoVo.getEmail();
        this.userNm = userInfoVo.getUserNm();
        this.email = userInfoVo.getEmail();
    }
}