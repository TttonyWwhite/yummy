package pers.illiant.yummy.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.illiant.yummy.dao.RestaurantMapper;
import pers.illiant.yummy.entity.Restaurant;
import pers.illiant.yummy.model.RestaurantVO_post;
import pers.illiant.yummy.model.RestaurantVO_register;
import pers.illiant.yummy.service.RestaurantService;
import pers.illiant.yummy.util.IDCreater;

import java.util.ArrayList;
import java.util.List;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantMapper restaurantMapper;

    @Override
    public boolean register(RestaurantVO_register restaurant) {
        String id = IDCreater.doGenRandomNum(7);
        Restaurant restaurant1 = new Restaurant(id, restaurant.getAddress(), restaurant.getType(), restaurant.getPhoneNumber(), restaurant.getLng_lat(), restaurant.getImgUrl(), restaurant.getShopName());
        try {
            restaurantMapper.insert(restaurant1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<RestaurantVO_post> getAll() {
        List<Restaurant> list = restaurantMapper.selectAll();
        List<RestaurantVO_post> retList = new ArrayList<>();
        for (Restaurant rest : list) {
            RestaurantVO_post vo = new RestaurantVO_post();
            vo.setShopName(rest.getShopName());
            vo.setImgUrl(rest.getImgurl());
            retList.add(vo);
        }

        return retList;
    }
}
