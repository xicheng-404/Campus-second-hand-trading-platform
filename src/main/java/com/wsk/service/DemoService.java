package com.wsk.service;

import com.jun.entity.GoodsWeight;
import com.wsk.bean.ShopInformationBean;
import com.wsk.pojo.ShopInformation;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DemoService {
    List<GoodsWeight> shopWeight (Integer uid);
    public List<ShopInformation> chaxun(List<GoodsWeight> list1);
}
