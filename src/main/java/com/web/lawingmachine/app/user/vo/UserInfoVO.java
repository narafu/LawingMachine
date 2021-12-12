package com.web.lawingmachine.app.user.vo;


import com.web.lawingmachine.app.common.vo.BaseVO;
import lombok.Data;

@Data
public class UserInfoVO extends BaseVO {

    private String userInfoSeq;
    private String userId;
    private String userNm;
    private String password;
    private String roleCd;

    private int takeRev;
}
