package com.web.lawingmachine.app.board.entity;

import com.web.lawingmachine.app.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "BRD_VIEWS")
@Data
public class BrdViewsEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer brdViewsSeq;

    private Integer brdMstrInfoSeq;

}
