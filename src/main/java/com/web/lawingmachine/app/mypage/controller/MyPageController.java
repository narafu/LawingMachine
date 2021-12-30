package com.web.lawingmachine.app.mypage.controller;

import com.web.lawingmachine.app.common.controller.BaseUtil;
import com.web.lawingmachine.app.security.SessionUser;
import com.web.lawingmachine.app.training.service.QuizService;
import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private BaseUtil baseUtil;

    @GetMapping("/myProfile")
    public String myProfile() {
        return "/view/mypage/myProfile";
    }

    @GetMapping("/reviewNote")
    public String reviewNote(HttpServletRequest req, QuizMstrInfoVO quizMstrInfoVO, Model model) {

        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        quizMstrInfoVO.setUserId(sessionUser.getUserId());
        model.addAttribute("quizMstrInfoVO", quizMstrInfoVO);

        // 공통코드(과목코드)
        List<Map<String, String>> CommSubjectList = baseUtil.selectCmmnCdList("002");
        model.addAttribute("CommSubjectList", CommSubjectList);

        return "/view/mypage/reviewNote";
    }

    @GetMapping("/reviewNote/data")
    public String reviewNoteAjax(QuizMstrInfoVO param, Model model) {

        // 과목명
        List<Map<String, String>> subjectList = quizService.selectSubjectList(param);
        model.addAttribute("subjectList", subjectList);

        // 정답률(차트)
        List<QuizMstrInfoVO> quizResultRatioList = quizService.selectQuizResultRatioList(param);
        model.addAttribute("quizResultRatioList", quizResultRatioList);

        return "/view/mypage/quizResultData";
    }

    @GetMapping("/quizResult")
    public String quizResult() {
        return "/view/mypage/quizResult";
    }

}
