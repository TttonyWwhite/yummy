package pers.illiant.yummy.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.illiant.yummy.model.OrderVO;
import pers.illiant.yummy.model.ProductVO;
import pers.illiant.yummy.service.ShoppingService;
import pers.illiant.yummy.util.Result;
import pers.illiant.yummy.util.ResultUtils;

import javax.annotation.Resource;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class ShoppingController {

    @Resource(name = "shoppingService")
    private ShoppingService shoppingService;

    @RequestMapping("/orderFoods")
    public Result orderFoods(@RequestBody OrderVO order) {
        //将ordervo中属于info表的存到info表中，product部分存到product表中
        shoppingService.orderFood(order);
        return ResultUtils.success();
    }

    @RequestMapping("/getOrders")
    public Result getOrders(@RequestParam Integer memberId) {

        return shoppingService.getOrders(memberId);
    }

    @RequestMapping("/getOrderDetail")
    public Result getOrderDetail(@RequestParam Integer orderId) {
        return shoppingService.getOrderDetail(orderId);
    }

    /**
     * 确认收货api
     * @param orderId 订单编号
     * @return 操作结果
     */
    @RequestMapping("/confirm")
    public Result confirm(@RequestParam Integer orderId) {
        return shoppingService.confirm(orderId);
    }
}
