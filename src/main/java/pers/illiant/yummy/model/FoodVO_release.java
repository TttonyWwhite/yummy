package pers.illiant.yummy.model;

public class FoodVO_release {
    private String foodName;
    private double price;
    private String imgUrl;
    private String restaurantId;

    public FoodVO_release() {
    }

    public FoodVO_release(String foodName, double price, String imgUrl, String restaurantId) {
        this.foodName = foodName;
        this.price = price;
        this.imgUrl = imgUrl;
        this.restaurantId = restaurantId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}
