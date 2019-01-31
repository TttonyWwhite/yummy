package pers.illiant.yummy.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.illiant.yummy.dao.FoodMapper;
import pers.illiant.yummy.entity.Food;
import pers.illiant.yummy.service.FoodService;

import java.util.List;

@Service("foodService")
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodMapper foodMapper;

    @Override
    public List<Food> getFoodOfRestaurant(String restaurant_id) {
        return foodMapper.selectByRestaurantId(restaurant_id);
    }
}
