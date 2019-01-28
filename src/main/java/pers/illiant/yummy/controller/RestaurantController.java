package pers.illiant.yummy.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import pers.illiant.yummy.entity.Restaurant;
import pers.illiant.yummy.model.FoodVO_release;
import pers.illiant.yummy.model.RestaurantVO;
import pers.illiant.yummy.model.RestaurantVO_post;
import pers.illiant.yummy.model.RestaurantVO_register;
import pers.illiant.yummy.service.RestaurantService;
import pers.illiant.yummy.util.Result;
import pers.illiant.yummy.util.ResultUtils;

import javax.annotation.Resource;
import java.util.List;


@RestController
@EnableAutoConfiguration
public class RestaurantController {
    @Resource(name = "restaurantService")
    private RestaurantService restaurantService;

    @RequestMapping("/signupForRestaurant")
    public boolean signupForRestaurant(@RequestBody RestaurantVO_register restaurant) {
        return restaurantService.register(restaurant);
    }

    @RequestMapping("/getAllShops")
    public Result getAllShops() {
        List<RestaurantVO_post> restList = restaurantService.getAll();

        return ResultUtils.success(restList);
    }

    @RequestMapping("/releaseFood")
    public Result releaseFood(@RequestBody FoodVO_release food) {
        return restaurantService.releaseFood(food);
    }


    //todo 研究下怎么不用requestbody实现
    @RequestMapping("/loginForRestaurant")
    public Result login(@RequestBody RestaurantVO restaurant) {
        return restaurantService.login(restaurant.getRestaurantId(), restaurant.getPassword());
    }
}
