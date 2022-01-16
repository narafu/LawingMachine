package com.web.lawingmachine.app.training.container;

import com.web.lawingmachine.app.common.service.BaseUtilService;
import com.web.lawingmachine.app.common.vo.ResultMessageVO;
import com.web.lawingmachine.app.security.SessionUser;
import com.web.lawingmachine.app.training.service.QuizService;
import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;
import com.web.lawingmachine.app.user.service.UserService;
import com.web.lawingmachine.app.user.vo.UserInfoVO;
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
    @Autowired
    private UserService userService;

    @GetMapping("/exam/notice")
    public String getExamNotice(HttpServletRequest req, Model model) {

        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        String userId = sessionUser.getUserId();

        QuizMstrInfoVO quizMstrInfoVO = new QuizMstrInfoVO();
        model.addAttribute("quizMstrInfoVO", quizMstrInfoVO);

        // 네비 리스트 (과목)
        List<QuizMstrInfoVO> quizSubjectNaviUserList = quizService.selectQuizSubjectUserList(userId);
        model.addAttribute("quizSubjectNaviUserList", quizSubjectNaviUserList);

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

        // 네비 리스트 (문제) - 문제번호 때문에
        List<QuizMstrInfoVO> quizAnswerNavList = quizService.selectAjaxQuizNavList(param);
        model.addAttribute("quizAnswerNavList", quizAnswerNavList);

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

        // 문제 조회 -  제출하기 때문에
        QuizMstrInfoVO quizMstrInfoVO = quizService.getAjaxQuizMstrInfo(param);
        model.addAttribute("quizMstrInfoVO", quizMstrInfoVO);

        return "view/training/quiz :: #quizAnswerNavListDiv";
    }

    @PostMapping("/exam/userAnswer")
    @ResponseBody
    public Integer saveUserAnswer(HttpServletRequest req, QuizMstrInfoVO param) {
        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        param.setUserId(sessionUser.getUserId());
        return quizService.saveUserAnswer(param);
    }

    @PostMapping("/exam/result")
    @ResponseBody
    public ResultMessageVO insertQuizResultInfo(HttpServletRequest req, QuizMstrInfoVO param) {

        ResultMessageVO result = new ResultMessageVO();

        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        param.setUserId(sessionUser.getUserId());

        int resultCnt = quizService.mergeQuizResultInfo(param);

        // 공통코드(과목코드)
        List<Map<String, String>> CommLst002 = baseUtilService.selectCmmnCdList("002");
        int totalSubjectCnt = CommLst002.size();
        int userSubjectCnt = quizService.getQuizResultUserSubjectCnt(param).size();

        // 전과목 제출했을 경우
        if (totalSubjectCnt == userSubjectCnt) {
            UserInfoVO userInfoVO = new UserInfoVO();
            userInfoVO.setUserId(sessionUser.getUserId());
            userInfoVO.setMembershipCd("30"); // 빌보드회원
            userService.updateMembershipCd(userInfoVO);
        }

        return result;
    }


}
