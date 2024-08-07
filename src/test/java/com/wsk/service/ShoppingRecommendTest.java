package com.wsk.service;


import com.jun.entity.Movie;
import com.jun.entity.ShoppingRec;
import com.jun.entity.User;
import com.wsk.bean.VO.ShoppingRecVo;
import com.wsk.dao.DemoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest(classes = ShoppingRecommendTest.class)
@RunWith(SpringRunner.class)
public class ShoppingRecommendTest {


    @Autowired
    private DemoMapper demoMapper;



    @Test
    public void ShoppingRecTest(){

        List<ShoppingRec> shoppingRecList = demoMapper.queryInfo();

        /**
         * Integer uid
         *
         * List<ShoppingRec> shoppingRecs
         */

        List<Movie> recommend = recommend(7,shoppingRecList);

    }


    public List<Movie> recommend(Integer uid, List<ShoppingRec> shoppingRecs) {
        //找到最近邻
        Map<Double, String> distances = computeNearestNeighbor(uid, shoppingRecs);
        String nearest = distances.values().iterator().next();
        System.out.println("最近邻 -> " + nearest);

        //找到最近邻看过，但是我们没看过的电影，计算推荐
        List<User> users = new Stack<>();
        String username = "";

        User neighborRatings = new User();
        for (User user:users) {
            if (nearest.equals(user.username)) {
                neighborRatings = user;
            }
        }
        System.out.println("最近邻看过的电影 -> " + neighborRatings.movieList);

        User userRatings = new User();
        for (User user:users) {
            if (username.equals(user.username)) {
                userRatings = user;
            }
        }
        System.out.println("用户看过的电影 -> " + userRatings.movieList);

        //根据自己和邻居的电影计算推荐的电影
        List<Movie> recommendationMovies = new ArrayList<>();
        for (Movie movie : neighborRatings.movieList) {
            if (userRatings.find(movie.movieName) == null) {
                recommendationMovies.add(movie);
            }
        }
        Collections.sort(recommendationMovies);
        return recommendationMovies;
    }



    private Map<Double, String> computeNearestNeighbor(Integer uid, List<ShoppingRec> shoppingRecs) {
        Map<Double, String> distances = new TreeMap<>();

        // 当前用户的 list
        List<User> users = new Stack<>();
        String username = "";

        // 计算与其他用户
        for (int i = 0; i < users.size(); i++) {
            User u2 = users.get(i);

            if (!u2.username.equals(username)) {
//                double distance = pearson_dis_shopping(u2.movieList, u1.movieList);
//                distances.put(distance, u2.username);
            }

        }
        System.out.println("该用户与其他用户的皮尔森相关系数 -> " + distances);
        return distances;
    }

    // 计算
    private double pearson_dis_shopping(List<ShoppingRecVo> rating1, List<ShoppingRecVo> rating2) {
        int n=rating1.size();
        List<Double> rating1ScoreCollect = rating1.stream().map(ShoppingRecVo::getWeight).collect(Collectors.toList());
        List<Double> rating2ScoreCollect = rating2.stream().map(ShoppingRecVo::getWeight).collect(Collectors.toList());

        double Ex= rating1ScoreCollect.stream().mapToDouble(x->x).sum();
        double Ey= rating2ScoreCollect.stream().mapToDouble(y->y).sum();
        double Ex2=rating1ScoreCollect.stream().mapToDouble(x->Math.pow(x,2)).sum();
        double Ey2=rating2ScoreCollect.stream().mapToDouble(y->Math.pow(y,2)).sum();
        double Exy= IntStream.range(0,n).mapToDouble(i->rating1ScoreCollect.get(i)*rating2ScoreCollect.get(i)).sum();
        double numerator=Exy-Ex*Ey/n;
        double denominator=Math.sqrt((Ex2-Math.pow(Ex,2)/n)*(Ey2-Math.pow(Ey,2)/n));
        if (denominator==0) return 0.0;
        return numerator/denominator;
    }






}
