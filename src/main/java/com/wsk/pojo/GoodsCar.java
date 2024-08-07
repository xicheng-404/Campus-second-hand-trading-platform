package com.wsk.pojo;

import cn.hutool.core.lang.WeightRandom;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class GoodsCar implements Serializable {
    private Integer id;

    private Date modified;

    private Integer sid;

    private Integer uid;

    private Integer quantity;

    private Integer display;

    private Integer weight;

}