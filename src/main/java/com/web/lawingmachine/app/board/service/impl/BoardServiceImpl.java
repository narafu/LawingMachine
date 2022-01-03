package com.web.lawingmachine.app.board.service.impl;

import com.web.lawingmachine.app.board.mapper.BrdMstrInfoMapper;
import com.web.lawingmachine.app.board.mapper.BrdViewsMapper;
import com.web.lawingmachine.app.board.service.BoardService;
import com.web.lawingmachine.app.board.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BrdMstrInfoMapper brdMstrInfoMapper;
    @Autowired
    private BrdViewsMapper brdViewsMapper;

    @Override
    public List<BoardVO> selectBoardList(BoardVO boardVO) {
        return brdMstrInfoMapper.selectBoardList(boardVO);
    }

    @Override
    public int getBoardListCnt(BoardVO boardVO) {
        return brdMstrInfoMapper.getBoardListCnt(boardVO);
    }

    @Override
    public BoardVO getBoardInfo(BoardVO boardVO) {
        return brdMstrInfoMapper.getBoardInfo(boardVO);
    }

    @Override
    public int insertBoardInfo(BoardVO param) {
        return brdMstrInfoMapper.insertBoardInfo(param);
    }

    @Override
    public int delBoardInfo(BoardVO param) {
        return brdMstrInfoMapper.delBoardInfo(param);
    }

    @Override
    public int updateBoardInfo(BoardVO param) {
        return brdMstrInfoMapper.updateBoardInfo(param);
    }

    @Override
    public int insertBrdViews(BoardVO param) {
        return brdViewsMapper.insertBrdViews(param);
    }

    @Override
    public int getBrdViews(BoardVO boardVO) {
        return brdViewsMapper.getBrdViews(boardVO);
    }
}
