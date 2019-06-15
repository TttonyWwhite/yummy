package pers.illiant.yummy.model;

import pers.illiant.yummy.entity.Restaurant;

import java.util.Random;

public class RestaurantVO_post {
    private String restaurantId;
    private String shopName;
    private int rate;
    private double deliveryCost;
    private String imgUrl;
    private int deliveryTime;
    private Random rand = new Random();

    //todo 需要加入rate字段，根据用户地址和店铺地址之间的距离计算配送费用

    public RestaurantVO_post() {
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public RestaurantVO_post(String shopName, int rate, double deliveryCost, String imgUrl, String restaurantId) {
        this.shopName = shopName;
        this.rate = rate;
        this.deliveryCost = deliveryCost;
        this.imgUrl = imgUrl;
        this.restaurantId = restaurantId;
    }

    public RestaurantVO_post(Restaurant restaurant) {
        this.shopName = restaurant.getShopName();
        this.imgUrl = restaurant.getImgurl();
        this.restaurantId = restaurant.getRestaurantId();
        //todo 要计算出rate和配送费用，现在先用一个虚拟值
        rate = rand.nextInt(5);
        deliveryCost = rand.nextInt(5);
        deliveryTime = rand.nextInt(40);
        if (deliveryTime < 20) deliveryTime += 20;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
