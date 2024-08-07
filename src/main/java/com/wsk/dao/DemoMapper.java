package com.wsk.dao;

import com.jun.entity.GoodsWeight;
import com.jun.entity.ShoppingRec;
import com.wsk.bean.ShopInformationBean;
import com.wsk.bean.VO.ShoppingRecVo;
import com.wsk.pojo.ShopInformation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DemoMapper {

    List<ShoppingRec> queryInfo ();

    List<ShopInformation> chaxun1(@Param("list1") List<GoodsWeight> list1);
}
