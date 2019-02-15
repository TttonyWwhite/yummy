package pers.illiant.yummy.util;

import pers.illiant.yummy.dao.OrderInfoMapper;
import pers.illiant.yummy.entity.OrderInfo;

import java.util.TimerTask;

public class CancelTimeTask extends TimerTask {

    private OrderInfoMapper orderInfoMapper;

    private int orderId;

    public CancelTimeTask(OrderInfoMapper orderInfoMapper,int orderId) {
        this.orderInfoMapper = orderInfoMapper;
        this.orderId = orderId;
    }

    @Override
    public void run() {
        OrderInfo info = orderInfoMapper.selectByPrimaryKey(orderId);
        info.setState("Canceled");
        orderInfoMapper.updateByPrimaryKey(info);
    }
}
