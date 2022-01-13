package com.web.lawingmachine.app.security;


import java.io.Serializable;

import com.web.lawingmachine.app.user.vo.UserInfoVO;

import lombok.Data;

@Data
public class SessionUser implements Serializable {

    private String userId;
    private String userNm;
    private String nickname;
    private String email;
    private String role;
    private String membershipCd;

    public SessionUser(UserInfoVO userInfoVo) {
        this.userId = userInfoVo.getEmail();
        this.userNm = userInfoVo.getUserNm();
        this.nickname = userInfoVo.getNickname();
        this.email = userInfoVo.getEmail();
        this.role = userInfoVo.getRole();
        this.membershipCd = userInfoVo.getMembershipCd();
    }
}
