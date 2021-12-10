package com.web.lawingmachine.app.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommController {

    @GetMapping(value = {"/", "index.do"})
    public String index() {
        return "view/common/index";
    }


}
