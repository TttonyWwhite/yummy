package pers.illiant.yummy.service;

import pers.illiant.yummy.entity.Restaurant;
import pers.illiant.yummy.model.FoodVO_release;
import pers.illiant.yummy.model.RestaurantVO_post;
import pers.illiant.yummy.model.RestaurantVO_register;
import pers.illiant.yummy.util.Result;

import java.util.List;

public interface RestaurantService {
    Result register(RestaurantVO_register restaurant);

    List<RestaurantVO_post> getAll();

    Result releaseFood(FoodVO_release food);

    Result login(String id, String password);

    Restaurant findById(String id);

    Result getOrders(String id);
}
