package pers.illiant.yummy.model;

public class RestaurantVO_update {
    private String restaurantId;
    private String shopName;
    private String address;
    private String lng_lat;
    private String imgUrl;
    private String type;
    private String phoneNumber;

    public RestaurantVO_update() {
    }

    public RestaurantVO_update(String restaurantId, String shopName, String address, String lng_lat, String imgUrl, String type, String phoneNumber) {
        this.restaurantId = restaurantId;
        this.shopName = shopName;
        this.address = address;
        this.lng_lat = lng_lat;
        this.imgUrl = imgUrl;
        this.type = type;
        this.phoneNumber = phoneNumber;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLng_lat() {
        return lng_lat;
    }

    public void setLng_lat(String lng_lat) {
        this.lng_lat = lng_lat;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
}
