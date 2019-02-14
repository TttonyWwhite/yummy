package pers.illiant.yummy.model;

public class AddressVO {
    private String address;
    private int addressId;

    public AddressVO() {
    }

    public AddressVO(String address, int addressId) {
        this.address = address;
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
