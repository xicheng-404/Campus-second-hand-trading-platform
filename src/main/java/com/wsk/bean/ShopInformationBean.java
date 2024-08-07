package com.wsk.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by wsk1103 on 2017/5/19.
 */
@Data
public class ShopInformationBean implements Serializable{
    private int id;
    private String name;
    private int level;
    private String remark;
    private double price;
    private String sort;
    private int quantity;
    private int transaction;
    private int uid;
    private String image;
    private Double weight;

}
