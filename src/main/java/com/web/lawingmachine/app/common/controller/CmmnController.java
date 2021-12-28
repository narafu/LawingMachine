package com.web.lawingmachine.app.common.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CmmnController {

    @GetMapping(value = {"/", "/index"})
    public String getAuthorizationMessage() {
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
