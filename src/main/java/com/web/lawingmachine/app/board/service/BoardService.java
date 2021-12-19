package com.web.lawingmachine.app.board.service;

import com.web.lawingmachine.app.board.vo.BoardVO;

import java.util.List;

public interface BoardService {

    List<BoardVO> selectBoardList(BoardVO boardVO);

    int getBoardListCnt(BoardVO boardVO);

    BoardVO getBoardInfo(BoardVO param);

    int insertBoardinfo(BoardVO param);
}
