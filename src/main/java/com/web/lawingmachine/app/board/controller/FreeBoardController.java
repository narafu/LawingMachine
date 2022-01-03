package com.web.lawingmachine.app.board.controller;

import com.web.lawingmachine.app.board.service.BoardService;
import com.web.lawingmachine.app.board.vo.BoardVO;
import com.web.lawingmachine.app.common.controller.BaseUtil;
import com.web.lawingmachine.app.common.vo.ResultMessageVO;
import com.web.lawingmachine.app.security.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board/free")
public class FreeBoardController {

    @Autowired
    private BaseUtil baseUtil;

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String boardList(BoardVO boardVO, Model model) {
        model.addAttribute("boardVO", boardVO);
        model.addAttribute("leftsidebarCd", "10");
        return "/view/board/boardList";
    }

    @ResponseBody
    @GetMapping("/list/ajax")
    public Map<String, Object> boardListAjax(BoardVO boardVO) {

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("offset", boardVO.getOffset());
        resultMap.put("pageSize", boardVO.getPageSize());

        List<BoardVO> list = boardService.selectBoardList(boardVO);
        resultMap.put("list", list);

        int total = boardService.getBoardListCnt(boardVO);
        resultMap.put("total", total);

        return resultMap;
    }

    @GetMapping("/inputForm")
    public String boardInputForm(BoardVO param, Model model) {

        // 공통코드(과목코드)
        List<Map<String, String>> subjectTypeCdList = baseUtil.selectCmmnCdList("002");
        model.addAttribute("subjectTypeCdList", subjectTypeCdList);

        // 공통코드(게시판코드)
        List<Map<String, String>> brdTypeCdList = baseUtil.selectCmmnCdList("003");
        model.addAttribute("brdTypeCdList", brdTypeCdList);

        BoardVO boardVO = new BoardVO();
        if (!StringUtils.isEmpty(param.getBrdMstrInfoSeq())) {
            boardVO = boardService.getBoardInfo(param);
        }

        model.addAttribute("boardVO", boardVO);

        return "/view/board/inputForm :: #boardContent";
    }

    @GetMapping("/infoView")
    public String boardinfoView(HttpServletRequest req, BoardVO param, Model model) {

        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        param.setRegistId(sessionUser.getUserId());
        param.setRegistNm(sessionUser.getNickname());

        BoardVO boardVO = boardService.getBoardInfo(param);
        model.addAttribute("boardVO", boardVO);

        // 조회수
        int resultCnt = boardService.getBrdViews(param);
        if (resultCnt == 0) {
            resultCnt = boardService.insertBrdViews(param);
        }

        return "/view/board/infoView :: #boardContent";
    }

    @PostMapping("/infoView/insert")
    @ResponseBody
    public ResultMessageVO insertBoardInfo(HttpServletRequest req, BoardVO param) {

        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        param.setRegistId(sessionUser.getUserId());
        param.setRegistNm(sessionUser.getNickname());

        ResultMessageVO result = new ResultMessageVO();
        int resultCnt = boardService.insertBoardInfo(param);

        if (resultCnt > 0) {
            result.setMessage("등록되었습니다.");
        } else {
            result.setMessage("오류가 발생하였습니다.");
        }
        return result;
    }

    @PostMapping("/infoView/update")
    @ResponseBody
    public ResultMessageVO updateBoardInfo(BoardVO param) {
        ResultMessageVO result = new ResultMessageVO();
        int resultCnt = boardService.updateBoardInfo(param);
        if (resultCnt > 0) {
            result.setMessage("저장되었습니다.");
        } else {
            result.setMessage("오류가 발생하였습니다.");
        }
        return result;
    }

    @RequestMapping("/infoView/delete")
    @ResponseBody
    public ResultMessageVO delBoardUnfo(BoardVO param) {

        ResultMessageVO result = new ResultMessageVO();
        int resultCnt = boardService.delBoardInfo(param);

        if (resultCnt > 0) {
            result.setMessage("삭제되었습니다.");
        } else {
            result.setMessage("오류가 발생하였습니다.");
        }

        return result;
    }

}
