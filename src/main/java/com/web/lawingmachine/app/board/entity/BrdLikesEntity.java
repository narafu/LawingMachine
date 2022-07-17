package com.web.lawingmachine.app.board.entity;

import com.web.lawingmachine.app.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BRD_LIKES")
@Data
public class BrdLikesEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int brdLikesSeq;

    private int brdMstrInfoSeq;

    @Column(length = 1)
    private char likeYn;
}
