package com.web.lawingmachine.app.board.vo;

import com.web.lawingmachine.app.common.vo.BaseVO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class BoardVO extends BaseVO {

    private Integer brdMstrInfoSeq;
    private Integer brdViewsSeq;

    private String brdTypeCd;
    private String brdTypeNm;
    private String subjectTypeCd;
    private String subjectTypeNm;
    private String title;
    private String content;
    private String noticeYn;

    private Integer brdLikesSeq;
    private int cmntCnt;
    private int likeTotalCnt;
    private int likeCnt;
    private int disLikeCnt;
    private String likeYn;

    private Integer brdCmntInfoSeq;
    private Integer brdViewSeq;
    private int viewCnt;
}
