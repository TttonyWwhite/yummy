package pers.illiant.yummy.service;


import org.springframework.stereotype.Service;
import pers.illiant.yummy.entity.Food;

import java.util.List;

@Service("foodService")
public interface FoodService {
    List<Food> getFoodOfRestaurant(String restaurant_id);
}
