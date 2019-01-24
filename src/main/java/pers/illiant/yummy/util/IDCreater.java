package pers.illiant.yummy.util;

public class IDCreater {
    public static String doGenRandomNum(int digits) {
        //todo 要保证不能重复
        java.util.Random r = new java.util.Random();
        int random = Math.abs(r.nextInt())% (int)(Math.pow(10, digits));
        String result = null;
        int length = (random+"").length();
        result = random*((int)Math.pow(10, digits-length))+"";
        return result;
    }

    public static void main(String args[]) {
        System.out.println(doGenRandomNum(7));
    }
}
