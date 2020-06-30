package com.csf2019.c1g72.burcev_d_e;


public class Main {

    public static void main(String[] args) {

        MyBigInteger val = new MyBigInteger("110");
        val.multiply(new MyBigInteger("11"));
        System.out.println(val.getValue());

    }
}
