package com.wsk.service.Impl;

import com.jun.Algorithm.RecommendShopping;
import com.jun.entity.GoodsWeight;
import com.jun.entity.ShoppingRec;
import com.wsk.bean.ShopInformationBean;
import com.wsk.dao.DemoMapper;
import com.wsk.pojo.ShopInformation;
import com.wsk.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wsk.dao.UserMapper;

import java.util.stream.Collectors;

import java.util.List;


@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public  List<GoodsWeight> shopWeight(Integer uid) {

        List<ShoppingRec> shoppingRecList = demoMapper.queryInfo();
        List<GoodsWeight> recommend = RecommendShopping.recommend(uid, shoppingRecList);
        System.out.println("输出结果");
        return recommend;
    }

    public List<ShopInformation> chaxun(List<GoodsWeight> list1){
        List<ShopInformation> chaxun1 = demoMapper.chaxun1(list1);
        return chaxun1;
    }
}
