package pers.illiant.yummy.model;

import pers.illiant.yummy.entity.Address;

import java.util.List;

//用于在个人资料页面展示的membervo
public class MemberVO_post {
    private int memberId;
    private String memberName;
    private String phoneNumber;
    private List<Address> addresses;

    public MemberVO_post() {
    }

    public MemberVO_post(int memberId, String memberName, String phoneNumber, List<Address> addresses) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
