package pers.illiant.yummy.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.illiant.yummy.dao.OrderInfoMapper;
import pers.illiant.yummy.dao.OrderProductMapper;
import pers.illiant.yummy.entity.OrderInfo;
import pers.illiant.yummy.entity.OrderProduct;
import pers.illiant.yummy.model.OrderVO;
import pers.illiant.yummy.service.ShoppingService;
import pers.illiant.yummy.util.Result;

import java.util.List;

@Service("shoppingService")
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    OrderInfoMapper orderInfoMapper;
    
    @Autowired
    OrderProductMapper orderProductMapper;

    @Override
    public Result orderFood(OrderVO vo) {
        List<Integer> idList = vo.getFoods();
        for (int i : idList) {
            //todo 把id写到order_product表中
        }

        return null;
    }
}
