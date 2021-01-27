package com.demo.ai.controller;

import org.apache.commons.lang3.ArrayUtils;

public class Test {
    public  static void main(String args[]){
        String hh = "你是不是人啊";
        String[] modals = new String[]{"啊"}; //语气词
        if(ArrayUtils.contains(modals, hh.substring(hh.length() - 1)) ){
            System.out.println(hh.substring(0,hh.length()-1));

        }
        System.out.println(hh.substring(0,hh.length()-1));
        ;
    }
}
