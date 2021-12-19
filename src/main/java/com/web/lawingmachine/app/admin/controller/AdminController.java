package com.web.lawingmachine.app.admin.controller;

import com.web.lawingmachine.app.admin.service.AdminService;
import com.web.lawingmachine.app.common.controller.BaseUtil;
import com.web.lawingmachine.app.common.vo.ResultMessageVO;
import com.web.lawingmachine.app.training.service.QuizService;
import com.web.lawingmachine.app.training.vo.QuizDtlInfoVO;
import com.web.lawingmachine.app.training.vo.QuizMstrInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BaseUtil baseUtil;

    @Autowired
    private AdminService adminService;

    @Autowired
    private QuizService quizService;

    @GetMapping("/quiz/list")
    public String boardList(QuizMstrInfoVO quizMstrInfoVO, Model model) {
        model.addAttribute("quizMstrInfoVO", quizMstrInfoVO);
        return "/view/admin/boardList";
    }

    @ResponseBody
    @GetMapping("/quiz/list/ajax")
    public Map<String, Object> boardListAjax(QuizMstrInfoVO quizMstrInfoVO) {

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("offset", quizMstrInfoVO.getOffset());
        resultMap.put("pageSize", quizMstrInfoVO.getPageSize());

        List<QuizMstrInfoVO> list = adminService.selectQuizList(quizMstrInfoVO);
        resultMap.put("list", list);

        int total = adminService.getQuizListCnt(quizMstrInfoVO);
        resultMap.put("total", total);

        return resultMap;
    }

    @GetMapping("/quiz/inputForm")
    public String boardInputForm(QuizMstrInfoVO param, Model model) {

        // 연도 리스트
        int curYear = LocalDate.now().getYear();
        int[] examYearArr = new int[5];
        for (int i = 0; i < examYearArr.length; i++) {
            examYearArr[i] = curYear - i;
        }
        model.addAttribute("examYearArr", examYearArr);

        // 공통코드(시험구분 코드)
        List<Map<String, String>> examCdList = baseUtil.selectCmmnCdList("001");
        model.addAttribute("examCdList", examCdList);

        // 시험회차 리스트
        int[] examNoArr = new int[10];
        for (int i = 0; i < examNoArr.length; i++) {
            examNoArr[i] = i + 1;
        }
        model.addAttribute("examNoArr", examNoArr);

        // 공통코드(과목 코드)
        List<Map<String, String>> subjectTypeCdList = baseUtil.selectCmmnCdList("002");
        model.addAttribute("subjectTypeCdList", subjectTypeCdList);

        // 공통코드(관리자게시판 코드)
        List<Map<String, String>> brdTypeCdList = baseUtil.selectCmmnCdList("004");
        model.addAttribute("brdTypeCdList", brdTypeCdList);

        QuizMstrInfoVO quizMstrInfoVO;
        if (!StringUtils.isEmpty(param.getQuizMstrInfoSeq())) {
            quizMstrInfoVO = adminService.getQuizInfo(param);
            quizMstrInfoVO.setQuizDtlList(quizService.selectQuizDtlList(param));
        } else {
            quizMstrInfoVO = new QuizMstrInfoVO();
            List<QuizDtlInfoVO> quizDtlList = new ArrayList<>();
            if (quizDtlList.isEmpty()) {
                quizDtlList.add(new QuizDtlInfoVO());
                quizDtlList.add(new QuizDtlInfoVO());
                quizDtlList.add(new QuizDtlInfoVO());
                quizDtlList.add(new QuizDtlInfoVO());
                quizDtlList.add(new QuizDtlInfoVO());
            }
            quizMstrInfoVO.setQuizDtlList(quizDtlList);
        }

        model.addAttribute("quizMstrInfoVO", quizMstrInfoVO);

        return "/view/admin/inputForm";
    }

    @GetMapping("/quiz/infoView")
    public String boardinfoView(QuizMstrInfoVO param, Model model) {

        QuizMstrInfoVO quizMstrInfoVO = adminService.getQuizInfo(param);
        quizMstrInfoVO.setQuizDtlList(quizService.selectQuizDtlList(param));
        model.addAttribute("quizMstrInfoVO", quizMstrInfoVO);

        return "/view/admin/infoView";
    }

    @PostMapping("/quiz/infoView")
    @ResponseBody
    public ResultMessageVO insertQuizMstrInfo(QuizMstrInfoVO param, Model model) {

        ResultMessageVO result = new ResultMessageVO();
        int resultCnt = adminService.insertQuizMstrInfo(param);

        if (resultCnt > 0) {
            result.setMessage("등록되었습니다.");
        } else {
            result.setMessage("오류가 발생하였습니다.");
        }

        return result;
    }

}
