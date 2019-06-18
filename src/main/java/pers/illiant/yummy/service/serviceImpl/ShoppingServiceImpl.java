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
import pers.illiant.yummy.util.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

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

    @Autowired
    YummyAccountMapper yummyAccountMapper;

    @Autowired
    IncomeMapper incomeMapper;

    private static final int FIFTEENMINS = 120000;

    private static final double SHARE_PERCENT = 0.15; //yummy平台的抽成

    @Override
    public Result orderFood(OrderVO order) {

       Address address = addressMapper.selectByPrimaryKey(order.getAddressId());
       Restaurant restaurant = restaurantMapper.selectByPrimaryKey(order.getRestaurantId());

       if (getDistance(address, restaurant) > 10000) {
           return ResultUtils.error(11124, "超出配送距离");
       } else {

           List<ProductVO> list = order.getItems();
           double price = 0;
           for (ProductVO item : list) {
               price += item.getPrice() * item.getQty();
           }

           Member member = memberMapper.selectByPrimaryKey(order.getMemberId());
           double freight = 0;
           double distance = getDistance(address, restaurant);
           if (distance < 1000)
               freight = 2;
           else if (distance < 3000)
               freight = 5;
           else if (distance < 10000)
               freight = 8;

           //根据用户级别进行打折
           DecimalFormat df = new DecimalFormat("0.00");
           if (member.getLevel() == 4) {
               price *= 0.85;
           } else if (member.getLevel() == 3) {
               price *= 0.9;
           } else if (member.getLevel() == 2) {
               price *= 0.95;
           }
           price = Double.parseDouble(df.format(price));


           OrderInfo info = new OrderInfo();
           //此时订单状态为NotPaid

           info = new OrderInfo(order.getMemberId(), order.getRestaurantId(), order.getOrderTime(), order.getExpectTime(), price, freight, "NotPaid", order.getAddressId());

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
            } else if (item.getState().equals("Refund")) {
                state = "已退款";
            }

            OrderVO_post vo = new OrderVO_post(item.getOrderId(), restaurant.getImgurl(), restaurant.getShopName(), formatter.format(item.getOrderTime()), item.getPrice() + item.getFreight(), state);
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

        //进行餐品结算
        Restaurant restaurant = restaurantMapper.selectByPrimaryKey(info.getRestaurantId());
        YummyAccount account = yummyAccountMapper.selectByPrimaryKey(1);
        DecimalFormat format = new DecimalFormat("0.00");

        double shareForYummy = Double.parseDouble(format.format(info.getPrice() * SHARE_PERCENT)); // 平台应该从订单中得到的抽成
        double shareForRestaurant = Double.parseDouble(format.format(info.getPrice() * (1 - SHARE_PERCENT))); //餐厅应该得到的份额

        Income income = new Income();
        income.setIncomeDate(new Date());
        income.setShare(shareForYummy);
        incomeMapper.insert(income);

        shareForYummy += account.getBalance();
        account.setBalance(shareForYummy);
        yummyAccountMapper.updateByPrimaryKey(account);

        shareForRestaurant += restaurant.getBalance();
        restaurant.setBalance(shareForRestaurant);
        restaurantMapper.updateByPrimaryKey(restaurant);



        return ResultUtils.success();
    }

    @Override
    public Result payForOrder(int orderId) {
        OrderInfo info = orderInfoMapper.selectByPrimaryKey(orderId);
        Member member = memberMapper.selectByPrimaryKey(info.getMemberId());
        if (member.getBalance() < info.getPrice() + info.getFreight()) {
            return ResultUtils.error(11125, "余额不足");
        }

        DecimalFormat df = new DecimalFormat("0.00");

        double new_balance = member.getBalance() - info.getFreight();
        new_balance -= info.getPrice();

        //避免出现浮点数位数问题
        new_balance = Double.parseDouble(df.format(new_balance));

        member.setBalance(new_balance);
        memberMapper.updateByPrimaryKey(member);
        info.setState("Paid");
        orderInfoMapper.updateByPrimaryKey(info);

        return ResultUtils.success();
    }

    @Override
    public Result refund(int orderId) {
        OrderInfo info = orderInfoMapper.selectByPrimaryKey(orderId);
        Member member = memberMapper.selectByPrimaryKey(info.getMemberId());
        DecimalFormat df = new DecimalFormat("0.00");
        double balance = member.getBalance();
        double total = info.getFreight() + info.getPrice();

        if (info.getState().equals("Paid")) {
            balance += total;
        } else if (info.getState().equals("Accepted")) {
            balance += Double.parseDouble(df.format(total * 0.8));
        } else {
            return ResultUtils.error(11126, "无法退款");
        }

        balance = Double.parseDouble(df.format(balance));

        member.setBalance(balance);
        memberMapper.updateByPrimaryKey(member);
        info.setState("Refund");
        orderInfoMapper.updateByPrimaryKey(info);

        return ResultUtils.success();
    }

    private double getDistance(Address address, Restaurant restaurant) {
        String[] strs = restaurant.getLngLat().split(",");
        double lng2 = Double.parseDouble(strs[0]);
        double lat2 = Double.parseDouble(strs[1]);


        double distance = DistanceCalculator.getDistance(address.getLat(), address.getLng(), lat2, lng2);
//        return !(distance > 10000);
        return distance;
    }

}
