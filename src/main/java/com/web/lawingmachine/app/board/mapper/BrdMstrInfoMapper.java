package com.web.lawingmachine.app.board.mapper;

import com.web.lawingmachine.app.board.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrdMstrInfoMapper {

    List<BoardVO> selectBoardList(BoardVO boardVO);

    int getBoardListCnt(BoardVO boardVO);

    BoardVO getBoardInfo(BoardVO boardVO);

    int insertBoardInfo(BoardVO param);

    int delBoardInfo(BoardVO param);

    int updateBoardInfo(BoardVO param);

    int insertBrdViews(BoardVO param);
}
