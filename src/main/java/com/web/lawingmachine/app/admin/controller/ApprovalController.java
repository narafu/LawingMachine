package com.web.lawingmachine.app.admin.controller;

import com.web.lawingmachine.app.admin.service.AdminService;
import com.web.lawingmachine.app.common.vo.ResultMessageVO;
import com.web.lawingmachine.app.user.service.UserService;
import com.web.lawingmachine.app.user.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/board/approval")
public class ApprovalController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String boardList(UserInfoVO userInfoVO, Model model) {
        model.addAttribute("userInfoVO", userInfoVO);
        model.addAttribute("leftsidebarCd", "20");
        return "/view/admin/approval/boardList";
    }

    @ResponseBody
    @GetMapping("/list/ajax")
    public Map<String, Object> boardListAjax(UserInfoVO param) {

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("offset", param.getOffset());
        resultMap.put("pageSize", param.getPageSize());

        List<UserInfoVO> list = adminService.selectApprovalList(param);
        resultMap.put("list", list);

        int total = adminService.getApprovalListCnt(param);
        resultMap.put("total", total);

        return resultMap;
    }

    @GetMapping("/infoView")
    public String myProfile(String userId, Model model) {
        UserInfoVO userInfo = userService.getUserInfo(userId);
        model.addAttribute("userInfo", userInfo);
        return "/view/admin/approval/infoView :: #boardContent";
    }

    @PostMapping("/infoView")
    @ResponseBody
    public ResultMessageVO approval(String[] userIdArr) {
        ResultMessageVO result = new ResultMessageVO();
        int resultCnt = 0;
        for (String userId : userIdArr) {
            resultCnt += adminService.updateMembershipCd(userId, "20");
        }
        if (resultCnt > 0) {
            result.setMessage("승인되었습니다.");
        } else {
            result.setMessage("오류가 발생하였습니다.");
        }
        return result;
    }

}
