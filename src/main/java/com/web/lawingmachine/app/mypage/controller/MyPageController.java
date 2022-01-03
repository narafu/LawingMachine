package com.web.lawingmachine.app.mypage.controller;

import com.web.lawingmachine.app.common.service.BaseUtilService;
import com.web.lawingmachine.app.common.vo.ModalVO;
import com.web.lawingmachine.app.common.vo.ResultMessageVO;
import com.web.lawingmachine.app.security.SessionUser;
import com.web.lawingmachine.app.training.service.QuizService;
import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;
import com.web.lawingmachine.app.user.service.UserService;
import com.web.lawingmachine.app.user.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    private UserService userService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private BaseUtilService baseUtilService;

    @GetMapping("/myprofile")
    public String myProfile(HttpServletRequest req, Model model) {
        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        UserInfoVO userInfo = userService.getUserInfo(sessionUser.getUserId());
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("leftsidebarCd", "10");
        return "/view/mypage/myprofile";
    }

    @PostMapping("/myprofile")
    @ResponseBody
    public ResultMessageVO updateUserInfo(UserInfoVO userInfoVO) {
        ResultMessageVO result = new ResultMessageVO();
        int resultCnt = userService.updateUserInfo(userInfoVO);
        if (resultCnt > 0) {
            result.setMessage("수정되었습니다.");
            result.setResultCode("SUCCESS");
        }
        return result;
    }

    @GetMapping("/reviewNote")
    public String reviewNote(HttpServletRequest req, QuizMstrInfoVO quizMstrInfoVO, Model model) {

        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        quizMstrInfoVO.setUserId(sessionUser.getUserId());
        model.addAttribute("quizMstrInfoVO", quizMstrInfoVO);

        // 공통코드(과목코드)
        List<Map<String, String>> CommLst002 = baseUtilService.selectCmmnCdList("002");
        model.addAttribute("CommLst002", CommLst002);

        model.addAttribute("leftsidebarCd", "20");

        return "/view/mypage/reviewNote";
    }

    @GetMapping("/reviewNote/data")
    public String reviewNoteData(QuizMstrInfoVO param, Model model) {

        // 과목명
        List<Map<String, String>> subjectList = quizService.selectSubjectList(param);
        model.addAttribute("subjectList", subjectList);

        // 정답률 리스트
        List<QuizMstrInfoVO> quizResultRatioList = quizService.selectQuizResultRatioList(param);
        model.addAttribute("quizResultRatioList", quizResultRatioList);

        return "/view/mypage/quizResultData";
    }

    @GetMapping("/quizResult")
    public String quizResult(ModelMap model) {
        model.addAttribute("leftsidebarCd", "30");
        return "/view/mypage/quizResult";
    }

    @RequestMapping("/quizResultInfoModal")
    public String quizResultInfoModal(HttpServletRequest req, QuizMstrInfoVO param, ModalVO modalVO, ModelMap model) {

        model.addAttribute("modalVO", modalVO);

        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        param.setUserId(sessionUser.getUserId());

        // 문제 조회
        QuizMstrInfoVO quizMstrInfoVO = quizService.getAjaxQuizMstrInfo(param);
        quizMstrInfoVO.setQuizNo(param.getQuizNo());
        model.addAttribute("quizMstrInfoVO", quizMstrInfoVO);

        return "view/modal/quizResultInfoModal";
    }

}
