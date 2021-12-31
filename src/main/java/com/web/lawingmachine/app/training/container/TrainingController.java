package com.web.lawingmachine.app.training.container;

import com.web.lawingmachine.app.common.service.BaseUtilService;
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
    private BaseUtilService baseUtilService;
    @Autowired
    private QuizService quizService;

    @GetMapping("/exam/notice")
    public String getExamNotice(Model model) {

        QuizMstrInfoVO quizMstrInfoVO = new QuizMstrInfoVO();
        model.addAttribute("quizMstrInfoVO", quizMstrInfoVO);

        // 네비 리스트 (과목)
        List<Map<String, String>> quizSubjectNaviList = quizService.selectQuizSubjectList();
        model.addAttribute("quizSubjectNaviList", quizSubjectNaviList);

        return "view/training/notice";
    }

    @GetMapping("/exam/quiz")
    public String getQuizMstrInfo(QuizMstrInfoVO param, Model model) {
        // 공통코드(시험구분코드)
        List<Map<String, String>> CommLst001 = baseUtilService.selectCmmnCdList("001");
        model.addAttribute("CommLst001", CommLst001);
        // 공통코드(과목코드)
        List<Map<String, String>> CommLst002 = baseUtilService.selectCmmnCdList("002");
        model.addAttribute("CommLst002", CommLst002);
        model.addAttribute("quizMstrInfoVO", param);
        return "view/training/quiz";
    }

    @GetMapping("/exam/quiz/ajax")
    public String getQuizMstrInfoAjax(HttpServletRequest req, QuizMstrInfoVO param, Model model) {

        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        param.setUserId(sessionUser.getUserId());

        // 문제 조회
        QuizMstrInfoVO quizMstrInfoVO = quizService.getAjaxQuizMstrInfo(param);
        model.addAttribute("quizMstrInfoVO", quizMstrInfoVO);

        return "view/training/quiz :: #quizMstrInfoDiv";
    }

    @GetMapping("/exam/quiz/nav/ajax")
    public String getQuizNavAjax(HttpServletRequest req, QuizMstrInfoVO param, Model model) {

        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        param.setUserId(sessionUser.getUserId());

        // 네비 리스트 (과목)
        List<Map<String, String>> quizSubjectNaviList = quizService.selectQuizSubjectList();
        model.addAttribute("quizSubjectNaviList", quizSubjectNaviList);

        // 네비 리스트 (문제)
        List<QuizMstrInfoVO> quizAnswerNavList = quizService.selectAjaxQuizNavList(param);
        model.addAttribute("quizAnswerNavList", quizAnswerNavList);

        return "view/training/quiz :: #quizAnswerNavListDiv";
    }

    @PostMapping("/exam/userAnswer")
    @ResponseBody
    public Integer saveUserAnswer(HttpServletRequest req, QuizMstrInfoVO param) {
        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        param.setUserId(sessionUser.getUserId());
        return quizService.saveUserAnswer(param);
    }

}
