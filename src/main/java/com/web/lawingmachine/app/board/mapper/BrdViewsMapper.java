package com.web.lawingmachine.app.board.mapper;

import com.web.lawingmachine.app.board.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrdViewsMapper {

    int insertBrdViews(BoardVO boardVO);

    int getBrdViews(BoardVO boardVO);
}
