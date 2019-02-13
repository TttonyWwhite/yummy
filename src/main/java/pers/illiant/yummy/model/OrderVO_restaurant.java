package pers.illiant.yummy.model;

import java.util.List;

//用于在餐厅后台系统首页显示的ordervo类
public class OrderVO_restaurant {
    private int orderId;
    private String time; //下单时间
    private String content; //订单内容
    private double price; //订单总价
    private String state; //订单当前状态

    public OrderVO_restaurant() {
    }

    public OrderVO_restaurant(int orderId, String time, String content, double price, String state) {
        this.orderId = orderId;
        this.time = time;
        this.content = content;
        this.price = price;
        this.state = state;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
