package com.web.lawingmachine.app.training.container;

import com.web.lawingmachine.app.security.SessionUser;
import com.web.lawingmachine.app.training.service.QuizService;
import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/exam")
    public String exam(Model model) {

        QuizMstrInfoVO quizMstrInfoVO = new QuizMstrInfoVO();
        model.addAttribute("quizMstrInfoVO", quizMstrInfoVO);

        // 네비 리스트 (과목)
        List<Map<String, String>> quizSubjectNaviList = quizService.selectQuizSubjectList();
        model.addAttribute("quizSubjectNaviList", quizSubjectNaviList);

        return "view/training/exam";
    }

    @GetMapping("/exam/quizMstrInfo")
    public String getQuizMstrInfo(HttpServletRequest req, QuizMstrInfoVO param, Model model) {

        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        param.setUserId(sessionUser.getUserId());

        // 네비 리스트 (과목)
        List<Map<String, String>> quizSubjectNaviList = quizService.selectQuizSubjectList();
        model.addAttribute("quizSubjectNaviList", quizSubjectNaviList);

        // 네비 리스트 (문제)
        List<QuizMstrInfoVO> quizAnswerNavList = quizService.selectAjaxQuizNavList(param);
        model.addAttribute("quizAnswerNavList", quizAnswerNavList);

        // 문제 조회
        QuizMstrInfoVO quizMstrInfoVO = quizService.getAjaxQuizMstrInfo(param);
        model.addAttribute("quizMstrInfoVO", quizMstrInfoVO);

        return "view/training/exam :: #quizMstrInfoDiv";
    }

    @PostMapping("/exam/userAnswer")
    @ResponseBody
    public Integer saveUserAnswer(HttpServletRequest req, QuizMstrInfoVO param) {
        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        param.setUserId(sessionUser.getUserId());
        return quizService.saveUserAnswer(param);
    }

}
