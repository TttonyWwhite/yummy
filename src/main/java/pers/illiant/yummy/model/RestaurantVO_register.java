package pers.illiant.yummy.model;

public class RestaurantVO_register {
    private String lng_lat;
    private String address;
    private String type;
    private String phoneNumber;
    private String imgUrl;
    private String shopName;

    public RestaurantVO_register() {
    }

    public RestaurantVO_register(String lng_lat, String address, String type, String phoneNumber, String imgUrl, String shopName) {
        this.lng_lat = lng_lat;
        this.address = address;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.imgUrl = imgUrl;
        this.shopName = shopName;
    }

    public String getLng_lat() {
        return lng_lat;
    }

    public void setLng_lat(String lng_lat) {
        this.lng_lat = lng_lat;
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
}
