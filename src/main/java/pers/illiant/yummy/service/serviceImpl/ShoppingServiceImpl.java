package pers.illiant.yummy.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.illiant.yummy.dao.MemberMapper;
import pers.illiant.yummy.dao.OrderInfoMapper;
import pers.illiant.yummy.dao.OrderProductMapper;
import pers.illiant.yummy.dao.RestaurantMapper;
import pers.illiant.yummy.entity.Member;
import pers.illiant.yummy.entity.OrderInfo;
import pers.illiant.yummy.entity.OrderProduct;
import pers.illiant.yummy.entity.Restaurant;
import pers.illiant.yummy.model.OrderVO;
import pers.illiant.yummy.model.OrderVO_post;
import pers.illiant.yummy.model.ProductVO;
import pers.illiant.yummy.service.ShoppingService;
import pers.illiant.yummy.util.MemberLevel;
import pers.illiant.yummy.util.Result;
import pers.illiant.yummy.util.ResultUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service("shoppingService")
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    OrderProductMapper orderProductMapper;

    @Autowired
    RestaurantMapper restaurantMapper;

    @Autowired
    MemberMapper memberMapper;

    @Override
    public Result orderFood(OrderVO order) {
       List<ProductVO> list = order.getItems();
       double price = 0;
       for (ProductVO item : list) {
           price += item.getPrice() * item.getQty();

       }

       OrderInfo info = new OrderInfo();
        //此时订单状态为paid
       info = new OrderInfo(order.getMemberId(), order.getRestaurantId(),order.getOrderTime(), order.getExpectTime(), price, order.getFreight(), "Paid", order.getAddress());


       orderInfoMapper.insert(info);

       for (ProductVO item : list) {
           OrderProduct product = new OrderProduct(info.getOrderId(), item.getTitle(), order.getRestaurantId(), item.getQty(), item.getPrice(), item.getId());
           orderProductMapper.insert(product);
       }

       //更新用户级别(可能用户级别提升)
       updateMemberLevel(order.getMemberId());

       return ResultUtils.success();
    }

    @Override
    public Result getOrders(Integer memberId) {
        List<OrderInfo> list = orderInfoMapper.selectByMemberId(memberId);
        List<OrderVO_post> retList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (OrderInfo item : list) {
            String restaurantId = item.getRestaurantId();
            Restaurant restaurant = restaurantMapper.selectByPrimaryKey(restaurantId);

            String state = "";
            if (item.getState().equals("NotPaid")) {
                state = "待付款";
            }else if (item.getState().equals("Paid")) {
                state = "已付款，待派送";
            } else if (item.getState().equals("Transporting")) {
                state = "派送中";
            } else if (item.getState().equals("Finished")) {
                state = "已完成";
            }

            OrderVO_post vo = new OrderVO_post(item.getOrderId(), restaurant.getImgurl(), restaurant.getShopName(), formatter.format(item.getOrderTime()), item.getPrice(), state);
            retList.add(vo);
        }

        return ResultUtils.success(retList);
    }

    @Override
    public void updateMemberLevel(int memberId) {
        Member member = memberMapper.selectByPrimaryKey(memberId);
        double totalConsumption = orderInfoMapper.getConsumption(memberId);
        if (totalConsumption >= 5000) {
            member.setLevel(MemberLevel.LEVEL_4);
        } else if (totalConsumption >= 1000) {
            member.setLevel(MemberLevel.LEVEL_3);
        } else if (totalConsumption >= 100) {
            member.setLevel(MemberLevel.LEVEL_2);
        } else {
            member.setLevel(MemberLevel.LEVLE_1);
        }

        memberMapper.updateByPrimaryKey(member);
    }
}
