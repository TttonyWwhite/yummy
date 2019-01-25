package pers.illiant.yummy.service;

import pers.illiant.yummy.model.RestaurantVO_post;
import pers.illiant.yummy.model.RestaurantVO_register;

import java.util.List;

public interface RestaurantService {
    boolean register(RestaurantVO_register restaurant);

    List<RestaurantVO_post> getAll();
}
