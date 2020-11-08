package com.grow.pay.connce.store;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsequencePay implements Serializable {

    private Long id;

    private Integer isConsequence;

    private String orderNo;

    private Integer isPerform;

    private Integer payWay;

    private Date createTime;

    private Date updateTime;
}
