package pers.illiant.yummy.model;

public class RequestVO {
    private String imgUrl; //店铺招牌图的url
    private String shopName;
    private String restaurantId;
    private String address;
    private String type;
    private String phoneNumber;
    private String requestTime;
    private int requestId;
    private String state;

    public RequestVO() {
    }

    public RequestVO(String imgUrl, String shopName, String restaurantId, String address, String type, String phoneNumber, String requestTime, int requestId, String state) {
        this.imgUrl = imgUrl;
        this.shopName = shopName;
        this.restaurantId = restaurantId;
        this.address = address;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.requestTime = requestTime;
        this.requestId = requestId;
        this.state = state;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
