package com.web.lawingmachine.app.board.entity;

import com.web.lawingmachine.app.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "BRD_LIKES")
@Data
public class BrdLikesEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer brdLikesSeq;

    private Integer brdMstrInfoSeq;

    @Column(length = 1)
    private char likeYn;
}
