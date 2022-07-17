package com.web.lawingmachine.app.board.entity;

import com.web.lawingmachine.app.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BRD_VIEWS")
@Data
public class BrdViewsEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int brdViewsSeq;

    private int brdMstrInfoSeq;

}
