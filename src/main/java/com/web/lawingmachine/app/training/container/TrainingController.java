package com.web.lawingmachine.app.training.container;

import com.web.lawingmachine.app.training.service.QuizService;
import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class TrainingController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/training/exam")
    public String exam(ModelMap model) {

        // 시험 조회(임시)
        QuizMstrInfoVO quizMstrInfoVO = new QuizMstrInfoVO();
        quizMstrInfoVO.setExamGrpCd("10");
        quizMstrInfoVO.setExamYear("2018");
        quizMstrInfoVO.setExamNo(7);
        quizMstrInfoVO.setSubjectTypeCd("10");
        model.addAttribute("quizMstrInfoVO", quizMstrInfoVO);

        return "view/training/exam";
    }

    @GetMapping("/training/exam/ajax/quizMstrInfo")
    public String getAjaxQuizMstrInfo(QuizMstrInfoVO param, ModelMap model) {

        // (임시)
        param.setUserId("narafu@kakao.com");

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

}
