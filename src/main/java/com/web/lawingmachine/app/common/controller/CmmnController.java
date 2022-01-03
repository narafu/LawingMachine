package com.web.lawingmachine.app.common.controller;

import com.web.lawingmachine.app.common.service.BaseUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class CmmnController {

    @Autowired
    private BaseUtilService baseUtilService;

    @GetMapping(value = {"/", "/index"})
    public String getAuthorizationMessage(Model model) {
        // 공통코드(과목코드)
        List<Map<String, String>> CommLst002 = baseUtilService.selectCmmnCdList("002");
        model.addAttribute("CommLst002", CommLst002);
        return "view/common/index";
    }

    @GetMapping("/login")
    public String login() {
        return "view/common/login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess() {
        return "view/common/success";
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        return "view/common/error";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

    @GetMapping("/reg")
    public String reg() {
        return "view/common/regForm";
    }

}
