package pers.illiant.yummy.model;

import pers.illiant.yummy.entity.Food;

//用于在店铺中展示信息的foodVO
public class FoodVO_post {
    private int id;
    private String restaurantId;
    private String title;
    private double price;
    private String image;

    public FoodVO_post() {
    }

    public FoodVO_post(int id, String restaurantId, String title, double price, String imgUrl) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.title = title;
        this.price = price;
        this.image = imgUrl;
    }

    public FoodVO_post(Food food) {
        this.id = food.getFoodid();
        this.restaurantId = food.getRestaurantId();
        this.title = food.getFoodname();
        this.price = food.getPrice();
        this.image = food.getImgurl();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}
