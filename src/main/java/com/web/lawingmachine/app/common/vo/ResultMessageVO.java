package com.web.lawingmachine.app.common.vo;

import lombok.Data;

@Data
public class ResultMessageVO {

    private String message;
    private int resultCnt;
    private String resultCode;
}
