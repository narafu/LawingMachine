package com.web.lawingmachine.app.common.vo;

import lombok.Data;

@Data
public class ModalVO {

	private String modalId;

	private String modalTitle;
	private String modalText;

	private String modalFunc;

	private String actBtnText;
	private String clsBtnText = "닫기";
}
