package pers.illiant.yummy.model;

import pers.illiant.yummy.entity.OrderInfo;

//用于在personalInfo界面展示订单简介的vo
public class OrderVO_post {
    private int orderId; //订单id
    private String image; //店铺logo的url
    private String title; //店铺名称
    private String time; //订单下达时间，要转换成yyyy-mm-dd hh:ss格式
    private double price; //总价
    private String state; //订单的状态

    public OrderVO_post() {
    }

    public OrderVO_post(int orderId, String image, String title, String time, double price, String state) {
        this.orderId = orderId;
        this.image = image;
        this.title = title;
        this.time = time;
        this.price = price;
        this.state = state;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
