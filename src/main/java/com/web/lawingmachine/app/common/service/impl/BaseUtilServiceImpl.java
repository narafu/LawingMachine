package com.web.lawingmachine.app.common.service.impl;

import com.web.lawingmachine.app.common.mapper.BaseUtilMapper;
import com.web.lawingmachine.app.common.service.BaseUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BaseUtilServiceImpl implements BaseUtilService {
	
	@Autowired
	private BaseUtilMapper baseUtilMapper;

	@Override
	public List<Map<String, String>> selectCmmnCdList(String grpCd) {
		return baseUtilMapper.selectCmmnCdList(grpCd);
	}

}
