package com.web.lawingmachine.app.common.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CMMN_DTL_CD")
@Data
public class CmmnDtlCdEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int cmmnDtlCdSeq;

    @Column(length = 3)
    private char grpCd; // 001: exam, 002: subject, 003: board, 004: admin

    @Column(length = 2)
    private char grpDtlCd;

    private String grpDtlNm;

    private int srtNo;

    @Column(length = 1)
    private char useYn;

}
