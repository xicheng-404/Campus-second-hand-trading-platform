package com.wsk.bean.VO;

import lombok.Data;

@Data
public class ShoppingRecVo {
    /// 用户id
    Integer uid;
    /// 商品id
    Integer sid;
    /// 商品名
    String name;
    // 用户名
    String username;
    // 商品权重
    Double weight;

}
