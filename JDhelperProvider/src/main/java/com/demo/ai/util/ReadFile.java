package com.demo.ai.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class ReadFile {

    public  String[] readfile(){
        String[]  geekModeSupportDomains =  null;

        try {
            InputStream in = this.getClass().getResourceAsStream("/domain/geekmode.json");
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            ObjectMapper obj = new ObjectMapper();
            geekModeSupportDomains = obj.readValue(new String(bytes),String[].class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return geekModeSupportDomains;
    }

    public String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
}
