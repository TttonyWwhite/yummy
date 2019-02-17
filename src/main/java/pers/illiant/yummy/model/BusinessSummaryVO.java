package pers.illiant.yummy.model;

public class BusinessSummaryVO {
    private String date; //日期
    private double total; //营业额

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
