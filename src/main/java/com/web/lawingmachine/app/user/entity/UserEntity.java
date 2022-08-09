package com.web.lawingmachine.app.user.entity;

import com.web.lawingmachine.app.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USER_INFO")
@Data
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private int userInfoSeq;

    private String userId;

    private String userNm;

    private String password;

    private String nickname;

    private String email;

    private String mobile;

    private String membershipCd;

    private String role;

    private int loginCnt;

    private String examTicketNo;

    private String examTicket;

    private String examTicketPath;
}
