package pers.illiant.yummy.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.illiant.yummy.dao.RestaurantMapper;
import pers.illiant.yummy.entity.Restaurant;
import pers.illiant.yummy.model.RestaurantVO_register;
import pers.illiant.yummy.service.RestaurantService;
import pers.illiant.yummy.util.IDCreater;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantMapper restaurantMapper;

    @Override
    public boolean register(RestaurantVO_register restaurant) {
        String id = IDCreater.doGenRandomNum(7);
        Restaurant restaurant1 = new Restaurant(id, restaurant.getAddress(), restaurant.getType(), restaurant.getPhoneNumber(), restaurant.getLng_lat(), restaurant.getImgUrl());
        try {
            restaurantMapper.insert(restaurant1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
