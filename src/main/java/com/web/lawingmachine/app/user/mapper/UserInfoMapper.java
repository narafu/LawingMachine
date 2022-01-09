package com.web.lawingmachine.app.user.mapper;

import com.web.lawingmachine.app.user.vo.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    int insertUserInfo(UserInfoVO userInfoVO);

    UserInfoVO getUserInfo(String userId);

    int updateUserInfo(UserInfoVO userInfoVO);

    int updateLoginUserInfo(UserInfoVO userInfoVO);

    List<UserInfoVO> selectApprovalList(UserInfoVO param);

    int getApprovalListCnt(UserInfoVO param);

    int updateMembershipCd(String userId, String membershipCd);

    int uploadImage(@Param("examTicketPath") String examTicketPath, @Param("examTicket") String filename, @Param("userId") String userId);
}
