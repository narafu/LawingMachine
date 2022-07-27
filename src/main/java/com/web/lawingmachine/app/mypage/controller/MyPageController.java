package com.web.lawingmachine.app.mypage.controller;

import com.web.lawingmachine.app.common.dto.CmmnCdDto;
import com.web.lawingmachine.app.common.service.BaseUtilService;
import com.web.lawingmachine.app.common.vo.ModalVO;
import com.web.lawingmachine.app.common.vo.ResultCode;
import com.web.lawingmachine.app.common.vo.ResultMessageVO;
import com.web.lawingmachine.app.exam.dto.QuizResultRatioDto;
import com.web.lawingmachine.app.exam.service.QuizService;
import com.web.lawingmachine.app.exam.vo.QuizMstrInfoVO;
import com.web.lawingmachine.app.security.SessionUser;
import com.web.lawingmachine.app.user.service.UserService;
import com.web.lawingmachine.app.user.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    private UserService userService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private BaseUtilService baseUtilService;

    @Value("${spring.servlet.multipart.location}")
    private String BASE_PATH;

    @GetMapping("/myprofile")
    public String myProfile(HttpServletRequest req, Model model) {
        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        UserInfoVO userInfo = userService.getUserInfo(sessionUser.getUserId());
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("leftsidebarCd", "10");
        model.addAttribute("headerActiveCode", "30");
        return "view/mypage/myprofile";
    }

    @PostMapping("/myprofile/uploadImage")
    @ResponseBody
    public ResultMessageVO uploadImage(HttpServletRequest req, @RequestParam("examTicketFile") MultipartFile imageFile)
            throws IOException {

        ResultMessageVO result = new ResultMessageVO();

        if (!imageFile.isEmpty()) {

            String filename = imageFile.getOriginalFilename();
            String ext = null;

            if (filename != null) {
                if (filename.contains(".")) {
                    ext = filename.substring(filename.lastIndexOf("."));
                } else {
                    ext = "";
                }
            }

            if (!".jpg".equals(ext) && !".jpeg".equals(ext) && !".png".equals(ext) && !".bmp".equals(ext)) {
                result.setMessage("이미지 파일을 업로드 해주세요.(.jpg, .jpeg, .png, .bmp)");
                result.setResultCode("FAIL");
                return result;
            }

            String dirPath = "/upload/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")); // 오늘날짜
            String filepath = BASE_PATH + dirPath;

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String nfileName = uuid + ext;

            File targetFile = new File(filepath, nfileName);

            if (!targetFile.exists()) {
                targetFile.mkdirs();
            } else {
                targetFile.delete();
            }

            try {
                imageFile.transferTo(targetFile);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }

            String examTicketPath = dirPath + "/" + nfileName;

            SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
            int resultCnt = userService.uploadImage(examTicketPath, filename, sessionUser.getUserId());
            if (resultCnt > 0) {
                result.setValue(examTicketPath);
            }
        }

        return result;
    }

    @PostMapping("/myprofile")
    @ResponseBody
    public ResultMessageVO updateUserInfo(UserInfoVO userInfoVO) {
        ResultMessageVO result = new ResultMessageVO();
        int resultCnt = userService.updateUserInfo(userInfoVO);
        if (resultCnt > 0) {
            result.setResultCode(String.valueOf(ResultCode.SUCCESS));
            result.setMessage("저장되었습니다.");
        } else {
            result.setResultCode(String.valueOf(ResultCode.FAIL));
            result.setMessage("오류가 발생하었습니다.");
        }
        return result;
    }

    @GetMapping("/reviewNote")
    public String reviewNote(HttpServletRequest req, QuizMstrInfoVO quizMstrInfoVO, Model model) {

        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        UserInfoVO userInfo = userService.getUserInfo(sessionUser.getUserId());
        model.addAttribute("userInfo", userInfo);

        quizMstrInfoVO.setUserId(sessionUser.getUserId());
        model.addAttribute("quizMstrInfoVO", quizMstrInfoVO);

        // 공통코드(과목코드)
        List<CmmnCdDto> CommLst002 = baseUtilService.selectCmmnCdList("002");
        model.addAttribute("CommLst002", CommLst002);
        model.addAttribute("leftsidebarCd", "30");
        model.addAttribute("headerActiveCode", "30");

        return "view/mypage/reviewNote";
    }

    @GetMapping("/reviewNote/data")
    public String reviewNoteData(QuizMstrInfoVO param, Model model) {

        // 과목명
        List<Map<String, String>> subjectList = quizService.selectSubjectList(param);
        model.addAttribute("subjectList", subjectList);

        // 정답률 리스트
        List<QuizResultRatioDto> quizResultRatioList = quizService.selectQuizResultRatioList(param);
        model.addAttribute("quizResultRatioList", quizResultRatioList);

        return "view/mypage/reviewNoteData";
    }

    @GetMapping("/quizResult")
    public String quizResult(HttpServletRequest req, ModelMap model) {
        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        UserInfoVO userInfo = userService.getUserInfo(sessionUser.getUserId());
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("leftsidebarCd", "20");
        model.addAttribute("headerActiveCode", "30");
        return "view/mypage/quizResult";
    }

    @GetMapping("/quizResult/data")
    @ResponseBody
    public Map<String, Object> quizResultData(HttpServletRequest req, QuizMstrInfoVO param) {

        Map<String, Object> result = new HashMap<>();

        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        String userId = sessionUser.getUserId();

        List<Map<String, Object>> quizResultList = quizService.selectQuizResultList(param);
        for (Map<String, Object> map : quizResultList) {
            if (userId.equals(map.get("USER_ID"))) {
                result = map;
                break;
            }
        }

        List<Map<String, Object>> quizResultInfo = quizService.getQuizResultInfo(userId);
        for (Map<String, Object> map : quizResultInfo) {
            result.put("subjectCd_" + (String) map.get("SUBJECT_TYPE_CD"), map.get("RESULT_CNT"));
        }

        int memberTotalCnt = quizService.getMemberTotalCnt(param);
        result.put("memberTotalCnt", memberTotalCnt);

        return result;
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
