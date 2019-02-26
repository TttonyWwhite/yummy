package pers.illiant.yummy.model;

//用于展示yummy平台营收状况的vo对象
public class FinancialVO {
    private String date; //日期
    private double income; //当天平台营收

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }
}
