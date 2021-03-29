package com.demo.ai.controller;

import com.demo.ai.entity.JdHelp;
import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Test {
    public  static void main(String args[]){
        HashMap<String, JdHelp> hmap = new HashMap<>();
        JdHelp jd = new JdHelp();
        jd.setUserMd5("12312");
        jd.setUserStatus("5");
        JdHelp jd2 = new JdHelp();
        jd2.setUserMd5("12312");
        jd2.setUserStatus("5");
        hmap.put("ddd",jd);
        hmap.put("ddd",jd2);
        Set<JdHelp> set = new HashSet<>();
        set.add(jd2);
        set.add(jd);
        String hh = "你是不是人啊";
        String[] modals = new String[]{"啊"}; //语气词
        if(ArrayUtils.contains(modals, hh.substring(hh.length() - 1)) ){
            System.out.println(hh.substring(0,hh.length()-1));

        }
        System.out.println(hh.substring(0,hh.length()-1));
        ;
    }
}
