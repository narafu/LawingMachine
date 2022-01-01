package com.web.lawingmachine.app.user.vo;


import com.web.lawingmachine.app.common.vo.BaseVO;
import lombok.Data;

@Data
public class UserInfoVO extends BaseVO {

    private String userInfoSeq;
    private String userId;
    private String userNm;
    private String email;
    private String mobile;
    private String password;
    private String role = "ROLE_USER";

    private int takeRev;
}
