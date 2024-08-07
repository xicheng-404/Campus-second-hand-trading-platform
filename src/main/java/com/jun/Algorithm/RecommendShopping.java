package com.jun.Algorithm;


import cn.hutool.core.collection.CollectionUtil;
import com.jun.entity.GoodsWeight;
import com.jun.entity.Movie;
import com.jun.entity.ShoppingRec;
import com.jun.entity.User;
import com.wsk.bean.VO.ShoppingRecVo;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RecommendShopping {
    public static List<GoodsWeight> recommend(Integer uid, List<ShoppingRec> shoppingRecs) {
        System.out.println(shoppingRecs);
        //找到最近邻
        Map<Double, Integer> distances = computeNearestNeighbor(uid, shoppingRecs);
        Integer nearest = distances.values().iterator().next();
        System.out.println("最近邻,用户id -> " + nearest);

        ShoppingRec neighbor = new ShoppingRec();

        for (ShoppingRec user : shoppingRecs) {
            if (nearest.equals(user.getUid())) {
                neighbor = user;
            }
        }

        System.out.println("最近邻收藏的商品信息 -> " + neighbor.getGoodsWeightsList());

        ShoppingRec userRatings = new ShoppingRec();
        for (ShoppingRec user : shoppingRecs) {
           if (uid.equals(user.getUid())) {
                userRatings = user;
                System.out.println("这里执行了");
            }
        }
        System.out.println("当前用户"+userRatings.getUid());
        System.out.println("当前用户收藏的商品信息 -> " + userRatings.getGoodsWeightsList());
        return CollectionUtil.subtractToList(neighbor.getGoodsWeightsList(), userRatings.getGoodsWeightsList());
    }



    private static Map<Double, Integer> computeNearestNeighbor(Integer uid, List<ShoppingRec> shoppingRecs) {

        Map<Double, Integer> distances = new TreeMap<>();

        ShoppingRec currentUser = shoppingRecs.stream().filter(shoppingRec -> uid.equals(shoppingRec.getUid())).findFirst().orElse(new ShoppingRec());
        List<ShoppingRec> otherUserShoppingList = shoppingRecs.stream().filter(shoppingRec -> !uid.equals(shoppingRec.getUid())).collect(Collectors.toList());

        otherUserShoppingList.stream().filter(shoppingRec -> !shoppingRec.getGoodsWeightsList().isEmpty())
        .forEach( k -> {
            double distance = pearson_dis_shopping(currentUser.getGoodsWeightsList(), k.getGoodsWeightsList());
            distances.put(distance,k.getUid());
        });
        System.out.println("该用户与其他用户的皮尔森相关系数 -> " + distances);
        return distances;
    }

    // 计算
    private static double pearson_dis_shopping(List<GoodsWeight> rating1, List<GoodsWeight> rating2) {
        int n=rating1.size();
        int m=rating2.size();

        List<Double> rating1ScoreCollect = rating1.stream().map(GoodsWeight::getWeight).collect(Collectors.toList());
        List<Double> rating2ScoreCollect = rating2.stream().map(GoodsWeight::getWeight).collect(Collectors.toList());

        double Ex= rating1ScoreCollect.stream().mapToDouble(x->x).sum();
        double Ey= rating2ScoreCollect.stream().mapToDouble(y->y).sum();
        double Ex2=rating1ScoreCollect.stream().mapToDouble(x->Math.pow(x,2)).sum();
        double Ey2=rating2ScoreCollect.stream().mapToDouble(y->Math.pow(y,2)).sum();
        double Exy= IntStream.range(0,(Math.min(m, n))).mapToDouble(i->rating1ScoreCollect.get(i)*rating2ScoreCollect.get(i)).sum();
        double numerator=Exy-Ex*Ey/n;
        double denominator=Math.sqrt((Ex2-Math.pow(Ex,2)/n)*(Ey2-Math.pow(Ey,2)/n));
        if (denominator==0) {return 0.0;}
        return numerator/denominator;
    }
}
