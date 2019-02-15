package pers.illiant.yummy.model;

public class RestaurantPositionVO {
    private String cityName; //市名
    private int restaurantNumber; //市内餐厅数量

    public RestaurantPositionVO() {
    }

    public RestaurantPositionVO(String cityName, int restaurantNumber) {
        this.cityName = cityName;
        this.restaurantNumber = restaurantNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getRestaurantNumber() {
        return restaurantNumber;
    }

    public void setRestaurantNumber(int restaurantNumber) {
        this.restaurantNumber = restaurantNumber;
    }
}
