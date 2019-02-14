package pers.illiant.yummy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class OrderInfo implements Serializable {

    public OrderInfo() {
    }

    public OrderInfo(Integer memberId, String restaurantId, Date orderTime, Date expectTime, Double price, Double freight, String state, int addressId) {
        this.memberId = memberId;
        this.restaurantId = restaurantId;
        this.orderTime = orderTime;
        this.expectTime = expectTime;
        this.price = price;
        this.freight = freight;
        this.state = state;
        this.addressId = addressId;
    }

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_id
     *
     * @mbg.generated
     */
    private Integer orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.member_id
     *
     * @mbg.generated
     */
    private Integer memberId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.restaurant_id
     *
     * @mbg.generated
     */
    private String restaurantId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_time
     *
     * @mbg.generated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.expect_time
     *
     * @mbg.generated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expectTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.price
     *
     * @mbg.generated
     */
    private Double price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.freight
     *
     * @mbg.generated
     */
    private Double freight;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.state
     *
     * @mbg.generated
     */
    private String state;

    private Integer addressId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order_info
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_id
     *
     * @return the value of order_info.order_id
     *
     * @mbg.generated
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_id
     *
     * @param orderId the value for order_info.order_id
     *
     * @mbg.generated
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.member_id
     *
     * @return the value of order_info.member_id
     *
     * @mbg.generated
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.member_id
     *
     * @param memberId the value for order_info.member_id
     *
     * @mbg.generated
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.restaurant_id
     *
     * @return the value of order_info.restaurant_id
     *
     * @mbg.generated
     */
    public String getRestaurantId() {
        return restaurantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.restaurant_id
     *
     * @param restaurantId the value for order_info.restaurant_id
     *
     * @mbg.generated
     */
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId == null ? null : restaurantId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_time
     *
     * @return the value of order_info.order_time
     *
     * @mbg.generated
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_time
     *
     * @param orderTime the value for order_info.order_time
     *
     * @mbg.generated
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.expect_time
     *
     * @return the value of order_info.expect_time
     *
     * @mbg.generated
     */
    public Date getExpectTime() {
        return expectTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.expect_time
     *
     * @param expectTime the value for order_info.expect_time
     *
     * @mbg.generated
     */
    public void setExpectTime(Date expectTime) {
        this.expectTime = expectTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.price
     *
     * @return the value of order_info.price
     *
     * @mbg.generated
     */
    public Double getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.price
     *
     * @param price the value for order_info.price
     *
     * @mbg.generated
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.freight
     *
     * @return the value of order_info.freight
     *
     * @mbg.generated
     */
    public Double getFreight() {
        return freight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.freight
     *
     * @param freight the value for order_info.freight
     *
     * @mbg.generated
     */
    public void setFreight(Double freight) {
        this.freight = freight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.state
     *
     * @return the value of order_info.state
     *
     * @mbg.generated
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.state
     *
     * @param state the value for order_info.state
     *
     * @mbg.generated
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", memberId=").append(memberId);
        sb.append(", restaurantId=").append(restaurantId);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", expectTime=").append(expectTime);
        sb.append(", price=").append(price);
        sb.append(", freight=").append(freight);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}