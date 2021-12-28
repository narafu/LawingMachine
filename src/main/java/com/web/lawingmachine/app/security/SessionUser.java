package com.web.lawingmachine.app.security;


import com.web.lawingmachine.app.user.vo.UserInfoVO;
import lombok.Data;

import java.io.Serializable;

@Data
public class SessionUser implements Serializable {

    private String name;
    private String email;
//    private String picture;

    public SessionUser(UserInfoVO userInfoVo) {
        this.name = userInfoVo.getUserNm();
        this.email = userInfoVo.getEmail();
//        this.picture = userInfoVo.getPicture();
    }
}