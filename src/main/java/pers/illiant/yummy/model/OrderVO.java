package pers.illiant.yummy.model;

import java.util.Date;
import java.util.List;

public class OrderVO {

    private List<ProductVO> items;
    private int orderId;
    private String restaurantId;
    private int memberId;
    private Date orderTime;
    private Date expectTime;
    private double freight; //运费
    private int addressId;


    public OrderVO() {
    }

    public OrderVO(List<ProductVO> items, String restaurantId, int memberId, Date orderTime, Date expectTime, double freight, int addressId) {
        this.items = items;
        this.restaurantId = restaurantId;
        this.memberId = memberId;
        this.orderTime = orderTime;
        this.expectTime = expectTime;
        this.freight = freight;
        this.addressId = addressId;
    }


    public List<ProductVO> getItems() {
        return items;
    }

    public void setItems(List<ProductVO> items) {
        this.items = items;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(Date expectTime) {
        this.expectTime = expectTime;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
