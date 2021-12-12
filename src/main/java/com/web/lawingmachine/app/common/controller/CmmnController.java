package com.web.lawingmachine.app.common.controller;

import com.web.lawingmachine.app.user.vo.UserInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CmmnController {

    @GetMapping(value = {"/", "index.do"})
    public String index(HttpServletRequest req, UserInfoVO param) {
        return "view/common/index";
    }


}
