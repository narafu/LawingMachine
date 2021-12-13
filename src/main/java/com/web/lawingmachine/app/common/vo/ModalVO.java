package com.web.lawingmachine.app.common.vo;

import lombok.Data;

@Data
public class ModalVO {

	private String modalId;

	private String modalTitle;
	private String modalText;

	private String modalFunc;

	private String actBtnText = "저장";
	private String clsBtnText = "닫기";
	private String okBtnText = "확인";
}
