package pers.illiant.yummy.model;

import java.util.Date;
import java.util.List;

public class OrderVO {
    private int member_id;
    private String restaurant_id;
    private Date order_time;
    private int order_id;
    private Date expect_time;
    private double price;
    private double freight; //配送费
    private String state; //订单状态，暂定为string类型

    //todo 可以只传food的id列表回来
    private List<Integer> foods;


    public OrderVO() {
    }

    public OrderVO(int member_id, String restaurant_id, Date order_time, Date expect_time, double price, double freight, String state) {
        this.member_id = member_id;
        this.restaurant_id = restaurant_id;
        this.order_time = order_time;
        this.expect_time = expect_time;
        this.price = price;
        this.freight = freight;
        this.state = state;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Date getExpect_time() {
        return expect_time;
    }

    public void setExpect_time(Date expect_time) {
        this.expect_time = expect_time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Integer> getFoods() {
        return foods;
    }

    public void setFoods(List<Integer> foods) {
        this.foods = foods;
    }
}
