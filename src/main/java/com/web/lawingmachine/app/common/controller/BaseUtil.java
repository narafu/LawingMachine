package com.web.lawingmachine.app.common.controller;

import com.web.lawingmachine.app.common.service.BaseUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class BaseUtil {

	@Autowired
	private BaseUtilService baseUtilService;

	public List<Map<String, String>> getCmmnCdList(String grpCd) {
		List<Map<String, String>> cmmnCdMapList = baseUtilService.selectCmmnCdList(grpCd);
		return cmmnCdMapList;
	}

}
