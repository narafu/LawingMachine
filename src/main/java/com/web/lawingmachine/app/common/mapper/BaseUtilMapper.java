package com.web.lawingmachine.app.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseUtilMapper {

    List<Map<String, String>> selectCmmnCdList(String grpCd);

}
