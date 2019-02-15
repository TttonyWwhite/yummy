package pers.illiant.yummy.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.illiant.yummy.dao.*;
import pers.illiant.yummy.entity.*;
import pers.illiant.yummy.model.OrderDetailVO;
import pers.illiant.yummy.model.OrderVO;
import pers.illiant.yummy.model.OrderVO_post;
import pers.illiant.yummy.model.ProductVO;
import pers.illiant.yummy.service.ShoppingService;
import pers.illiant.yummy.util.CancelTimeTask;
import pers.illiant.yummy.util.MemberLevel;
import pers.illiant.yummy.util.Result;
import pers.illiant.yummy.util.ResultUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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

    @Autowired
    AddressMapper addressMapper;

    private static final int FIFTEENMINS = 10000;

    @Override
    public Result orderFood(OrderVO order) {
       List<ProductVO> list = order.getItems();
       double price = 0;
       for (ProductVO item : list) {
           price += item.getPrice() * item.getQty();
       }

        //根据用户级别进行打折
       DecimalFormat df = new DecimalFormat("0.00");
       Member member = memberMapper.selectByPrimaryKey(order.getMemberId());
       if (member.getLevel() == 4) {
           price *= 0.85;
       } else if (member.getLevel() == 3) {
           price *= 0.92;
       } else if (member.getLevel() == 2) {
           price *= 0.95;
       }
       price = Double.parseDouble(df.format(price));


       OrderInfo info = new OrderInfo();
        //此时订单状态为NotPaid
       info = new OrderInfo(order.getMemberId(), order.getRestaurantId(),order.getOrderTime(), order.getExpectTime(), price, order.getFreight(), "NotPaid", order.getAddress());


       orderInfoMapper.insert(info);

       for (ProductVO item : list) {
           OrderProduct product = new OrderProduct(info.getOrderId(), item.getTitle(), order.getRestaurantId(), item.getQty(), item.getPrice(), item.getId());
           orderProductMapper.insert(product);
       }

       //更新用户级别(可能用户级别提升)
       updateMemberLevel(order.getMemberId());

       Timer timer = new Timer();
       timer.schedule(new CancelTimeTask(orderInfoMapper, info.getOrderId()), FIFTEENMINS);

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
                state = "订单已提交";
            } else if (item.getState().equals("Accepted")) {
                state = "商家已接单";
            } else if (item.getState().equals("Arrived")) {
                state = "已送达";
            } else if (item.getState().equals("Canceled")) {
                state = "支付超时";
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

    @Override
    public Result getOrderDetail(int orderId) {
        OrderDetailVO detail = new OrderDetailVO();
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
        Address address = addressMapper.selectByPrimaryKey(orderInfo.getAddressId());
        Member member = memberMapper.selectByPrimaryKey(orderInfo.getMemberId());
        Restaurant restaurant = restaurantMapper.selectByPrimaryKey(orderInfo.getRestaurantId());

        List<OrderProduct> products = orderProductMapper.selectByOrderId(orderId);
        List<ProductVO> voList = new ArrayList<>();

        detail.setAddress(address.getAddress());
        detail.setContactName(address.getContactName());
        detail.setFreight(orderInfo.getFreight());
        detail.setPhoneNumber(address.getPhoneNumber());
        detail.setImageUrl(restaurant.getImgurl());
        detail.setShopName(restaurant.getShopName());
        detail.setState(orderInfo.getState());

        for (OrderProduct item : products) {
            voList.add(new ProductVO(item));
        }

        detail.setProducts(voList);

        double discount = 1;
        if (member.getLevel() == 2)
            discount = 0.95;
        else if (member.getLevel() == 3)
            discount = 0.92;
        else if (member.getLevel() == 4)
            discount = 0.85;

        detail.setDiscount(discount);

        return ResultUtils.success(detail);
    }

    @Override
    public Result confirm(int orderId) {
        OrderInfo info = orderInfoMapper.selectByPrimaryKey(orderId);
        info.setState("Arrived"); //已送达
        orderInfoMapper.updateByPrimaryKey(info);

        return ResultUtils.success();
    }

    @Override
    public Result payForOrder(int orderId) {
        OrderInfo info = orderInfoMapper.selectByPrimaryKey(orderId);
        info.setState("Paid");
        orderInfoMapper.updateByPrimaryKey(info);

        return ResultUtils.success();
    }

}
