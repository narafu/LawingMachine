package com.web.lawingmachine.app.board.vo;

import com.web.lawingmachine.app.common.vo.BaseVO;
import lombok.Data;

@Data
public class BoardVO extends BaseVO {

    private String brdMstrInfoSeq;
    private String brdTypeCd;
    private String brdTypeNm;
    private String subjectTypeCd;
    private String subjectTypeNm;
    private String title;
    private String content;
    private String noticeYn;

    private String brdLikesSeq;
    private int cmntCnt;
    private int likeTotalCnt;
    private int likeCnt;
    private int disLikeCnt;
    private String likeYn;

    private String brdCmntInfoSeq;
    private String brdViewSeq;
    private int viewCnt;
}
