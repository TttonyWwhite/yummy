package pers.illiant.yummy.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.illiant.yummy.model.RestaurantVO_register;
import pers.illiant.yummy.service.RestaurantService;

import javax.annotation.Resource;


@RestController
@EnableAutoConfiguration
public class RestaurantController {
    @Resource(name = "restaurantService")
    private RestaurantService restaurantService;

    @RequestMapping("/signupForRestaurant")
    public boolean signupForRestaurant(@RequestBody RestaurantVO_register restaurant) {
        return restaurantService.register(restaurant);
    }
}
