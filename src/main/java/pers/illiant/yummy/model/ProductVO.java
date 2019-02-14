package pers.illiant.yummy.model;

import pers.illiant.yummy.entity.OrderProduct;

public class ProductVO {
    private int id;
    private String title;
    private double price;
    private String image;
    private int qty;

    public ProductVO() {
    }

    public ProductVO(int id, String title, double price, String image, int qty) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.qty = qty;
    }

    public ProductVO(OrderProduct product) {
        this.id = product.getFoodId();
        this.title = product.getFoodName();
        this.price = product.getFoodPrice();
        this.qty = product.getFoodQuantity();
        this.image = "";
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
