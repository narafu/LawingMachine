package com.web.lawingmachine.app.board.mapper;

import com.web.lawingmachine.app.board.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardVO> selectBoardList(BoardVO boardVO);

    int getBoardListCnt(BoardVO boardVO);

    BoardVO getBoardInfo(BoardVO boardVO);

    int insertBoardinfo(BoardVO param);
}
