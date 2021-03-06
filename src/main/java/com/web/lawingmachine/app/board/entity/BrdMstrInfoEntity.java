package com.web.lawingmachine.app.board.entity;

import com.web.lawingmachine.app.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BRD_MSTR_INFO")
@Data
public class BrdMstrInfoEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int brdMstrInfoSeq;

    @Column(length = 10)
    private String brdTypeCd;

    @Column(length = 10)
    private String subjectTypeCd;

    private String title;

    private String content;

    @Column(length = 1)
    private char noticeYn;

}
