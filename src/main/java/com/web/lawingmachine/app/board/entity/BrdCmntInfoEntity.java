package com.web.lawingmachine.app.board.entity;

import com.web.lawingmachine.app.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BRD_CMNT_INFO")
@Data
public class BrdCmntInfoEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int brdCmntInfoSeq;

    private int brdMstrInfoSeq;

}
