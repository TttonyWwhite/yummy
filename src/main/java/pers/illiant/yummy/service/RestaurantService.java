package pers.illiant.yummy.service;

import pers.illiant.yummy.entity.Restaurant;
import pers.illiant.yummy.model.*;
import pers.illiant.yummy.util.Result;

import java.util.List;

public interface RestaurantService {
    Result register(RestaurantVO_register restaurant);

    List<RestaurantVO_post> getAll();

    List<RestaurantVO_post> getShopsByType(String type);

    Result releaseFood(FoodVO_release food);

    Result login(String id, String password);

    Restaurant findById(String id);

    Result getOrders(String id);

    Result acceptOrder(int orderId);

    /**
     * 处理餐厅发送的修改信息请求
     */
    Result updateRestaurant(RestaurantVO_update restaurantVO);

    List<RestaurantVO_post> searchShop(String shopName);

    List<RestaurantVO_post> getShopsByPosition(double lng, double lat);
}
