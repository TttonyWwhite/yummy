package pers.illiant.yummy.service;

import pers.illiant.yummy.model.OrderVO;
import pers.illiant.yummy.util.Result;

public interface ShoppingService {
    Result orderFood(OrderVO vo);

    Result getOrders(Integer memberId);
}
