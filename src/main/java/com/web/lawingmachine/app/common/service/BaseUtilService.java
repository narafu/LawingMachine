package com.web.lawingmachine.app.common.service;

import com.web.lawingmachine.app.common.dto.CmmnCdDto;

import java.util.List;
import java.util.Map;


public interface BaseUtilService {

    List<CmmnCdDto> selectCmmnCdList(String grpCd);

}
