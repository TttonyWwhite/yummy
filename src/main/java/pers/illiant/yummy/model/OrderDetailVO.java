package pers.illiant.yummy.model;

import java.util.List;

public class OrderDetailVO  {
    private String state;
    private String imageUrl; //店铺招牌图的url
    private String shopName; //店铺名称
    private List<ProductVO> products;

    private double freight; //配送费
    private String contactName; //联系人
    private String phoneNumber; //
}
