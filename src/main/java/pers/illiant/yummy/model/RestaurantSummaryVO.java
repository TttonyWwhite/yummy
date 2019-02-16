package pers.illiant.yummy.model;

public class RestaurantSummaryVO {
    private String summaryItem; //统计项
    private int restaurantNumber; //餐厅数

    public String getSummaryItem() {
        return summaryItem;
    }

    public void setSummaryItem(String summaryIten) {
        this.summaryItem = summaryIten;
    }

    public int getRestaurantNumber() {
        return restaurantNumber;
    }

    public void setRestaurantNumber(int restaurantNumber) {
        this.restaurantNumber = restaurantNumber;
    }
}
