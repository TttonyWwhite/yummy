package pers.illiant.yummy.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.illiant.yummy.dao.OrderInfoMapper;
import pers.illiant.yummy.dao.OrderProductMapper;
import pers.illiant.yummy.entity.OrderInfo;
import pers.illiant.yummy.entity.OrderProduct;
import pers.illiant.yummy.model.OrderVO;
import pers.illiant.yummy.model.ProductVO;
import pers.illiant.yummy.service.ShoppingService;
import pers.illiant.yummy.util.DateFormater;
import pers.illiant.yummy.util.Result;

import java.text.ParseException;
import java.util.List;

@Service("shoppingService")
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    OrderProductMapper orderProductMapper;

    @Override
    public Result orderFood(OrderVO order) {
       List<ProductVO> list = order.getItems();
       double price = 0;
       for (ProductVO item : list) {
           price += item.getPrice() * item.getQty();

       }

       OrderInfo info = new OrderInfo();

       info = new OrderInfo(order.getMemberId(), order.getRestaurantId(),order.getOrderTime(), order.getExpectTime(), price, order.getFreight(), "active");


       int orderId = orderInfoMapper.insert(info);

       for (ProductVO item : list) {
           OrderProduct product = new OrderProduct(orderId, item.getTitle(), order.getRestaurantId(), item.getQty(), item.getPrice(), item.getId());
           orderProductMapper.insert(product);
       }

       return null;
    }
}
