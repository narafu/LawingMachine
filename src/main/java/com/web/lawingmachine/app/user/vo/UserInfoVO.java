package com.web.lawingmachine.app.user.vo;


import com.web.lawingmachine.app.common.vo.BaseVO;
import lombok.Data;

@Data
public class UserInfoVO extends BaseVO {

    private String userInfoSeq;
    private String userId;
    private String userNm;
    private String nickname;
    private String email;
    private String mobile;
    private int loginCnt;
    private String membershipCd;
    private String membershipNm;
    private String examTicket;
    private String examTicketPath;
    private String examTicketNo;
    private String examTicketYn;
    private String role;

    private String password;
    private int takeRev;
}
