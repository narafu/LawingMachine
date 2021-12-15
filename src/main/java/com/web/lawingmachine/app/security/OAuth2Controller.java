package com.web.lawingmachine.app.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2Controller {

    @GetMapping({"", "/"})
    public String getAuthorizationMessage() {
        return "view/common/index";
    }

    @GetMapping("/login")
    public String login() {
        return "view/common/login";
    }

    @GetMapping({"/loginSuccess", "/hello"})
    public String loginSuccess() {
        return "view/common/success";
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        return "view/common/error";
    }

}
