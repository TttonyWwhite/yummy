package pers.illiant.yummy.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.illiant.yummy.model.OrderVO;
import pers.illiant.yummy.util.Result;
import pers.illiant.yummy.util.ResultUtils;

@RestController
@EnableAutoConfiguration
public class ShoppingController {

    @RequestMapping("/orderFoods")
    public Result orderFoods(@RequestBody OrderVO order) {
        //将ordervo中属于info表的存到info表中，product部分存到product表中
        

        return ResultUtils.success();
    }
}
