package com.web.lawingmachine.app.board.service.impl;

import com.web.lawingmachine.app.board.service.BoardService;
import com.web.lawingmachine.app.board.mapper.BoardMapper;
import com.web.lawingmachine.app.board.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardVO> selectBoardList(BoardVO boardVO) {
        return boardMapper.selectBoardList(boardVO);
    }

    @Override
    public int getBoardListCnt(BoardVO boardVO) {
        return boardMapper.getBoardListCnt(boardVO);
    }

    @Override
    public BoardVO getBoardInfo(BoardVO boardVO) {
        return boardMapper.getBoardInfo(boardVO);
    }

    @Override
    public int insertBoardinfo(BoardVO param) {
        return boardMapper.insertBoardinfo(param);
    }
}
