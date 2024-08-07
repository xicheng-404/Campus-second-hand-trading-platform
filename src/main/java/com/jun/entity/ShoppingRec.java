package com.jun.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ShoppingRec {
    // uid
    private Integer uid;

    private String username;

    private String phone;

    private String realname;

    private Integer id;

    private Date modified;

    private String name;

    private Integer level;

    private String remark;

    private BigDecimal price;

    private Integer sort;

    private Integer display;

    private Integer quantity;

    private Integer transaction;

    private String image;

    private Integer sales;

    private String thumbnails;

    private List<GoodsWeight> goodsWeightsList;
}
