package com.web.lawingmachine.app.common.vo;

import lombok.Data;

@Data
public class BaseVO {

	private int rno;
	private int pageSize = 10;
	private int offset;

	private String delYn;
	private String registId;
	private String registNm;
	private String regDt;
	private String regTs;
	private String mdfyTs;
	private String delTs;

}
