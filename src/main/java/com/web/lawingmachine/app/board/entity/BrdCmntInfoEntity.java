package com.web.lawingmachine.app.board.entity;

import com.web.lawingmachine.app.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "BRD_CMNT_INFO")
@Data
public class BrdCmntInfoEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer brdCmntInfoSeq;

    private Integer brdMstrInfoSeq;

}
