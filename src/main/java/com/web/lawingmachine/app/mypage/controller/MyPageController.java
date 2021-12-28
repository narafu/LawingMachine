package com.web.lawingmachine.app.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @GetMapping("/myProfile")
    public String myProfile() {
        return "/view/mypage/myProfile";
    }

    @GetMapping("/reviewNote")
    public String reviewNote() {
        return "/view/mypage/reviewNote";
    }

    @GetMapping("/quizResult")
    public String quizResult() {
        return "/view/mypage/quizResult";
    }

}
