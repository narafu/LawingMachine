package com.web.lawingmachine.app.common.mapper;

import com.web.lawingmachine.app.common.dto.CmmnCdDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseUtilMapper {

    List<CmmnCdDto> selectCmmnCdList(String grpCd);

}
