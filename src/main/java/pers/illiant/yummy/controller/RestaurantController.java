package pers.illiant.yummy.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.illiant.yummy.entity.Restaurant;
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
}
