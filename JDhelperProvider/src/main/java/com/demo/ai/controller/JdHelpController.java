package com.demo.ai.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.ai.entity.TextClassify;
import com.demo.ai.service.JdHelpService;
import com.demo.ai.util.OkHttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jd_plantBean(JdHelp)表控制层
 *
 * @author makejava
 * @since 2020-12-24 16:53:28
 */
@RestController
@RequestMapping("help")
public class JdHelpController {
    /**
     * 服务对象
     */
    @Resource
    private JdHelpService jdHelpService;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("read")
    public void selectOne() {
        List<String> resList = new ArrayList<>();
        Map<String, String> resMap = new HashMap<>();
        String[] domaintext = readfile();
        System.out.println(domaintext);
        for (String dod : domaintext) {
            Map<String, String> paramte = new HashMap<>();
            paramte.put("text", dod);
            paramte.put("nbest", "3");
            String str = OkHttpUtil.postJsonParams("", JSONObject.toJSONString(paramte));
          //  System.out.println("getTextClassifies:<URL=>=(),paramte=(" + paramte.toString() + ")");
            if (StringUtils.isNotEmpty(str)) {
                String jsonArrayString = JSONObject.parseObject(str).getOrDefault("result", null).toString();
                List<TextClassify> resultData = JSONObject.parseArray(jsonArrayString, TextClassify.class);
                String lable = "";
                for (TextClassify ff : resultData) {
                    if (lable.equals("")) {
                        lable = ff.getLabel();
                    } else {
                       // lable = lable + "," + ff.getLabel();
                    }
                }

                resList.add(jsonArrayString);
                resMap.put(dod, lable);
            }
        }

        for (Map.Entry<String,String> entry : resMap.entrySet()) {
            if(entry.getValue().contains("")){
                System.out.println(entry.getKey()+"           "+entry.getValue());

            }
        }
//        System.out.println(resList);

    }


    public String[] readfile() {
        String[] geekModeSupportDomains = null;

        try {
            InputStream in = this.getClass().getResourceAsStream("/domain/weather.txt");
            byte[] bytes = new byte[in.available()];
            String red = new String(bytes, "UTF-8");

            in.read(bytes);
            geekModeSupportDomains = (new String(bytes)).split("\\r?\\n");

            //  ObjectMapper obj = new ObjectMapper();
            // geekModeSupportDomains = obj.readValue(new String(bytes),String[].class);

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