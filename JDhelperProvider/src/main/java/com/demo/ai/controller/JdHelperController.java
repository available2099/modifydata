package com.demo.ai.controller;

import com.demo.ai.entity.JdFruit;
import com.demo.ai.producer.RabbitSender;
import com.demo.ai.service.JdFruitService;
import com.demo.ai.service.JdPetService;
import com.demo.ai.service.JdPlantbeanService;
import com.demo.ai.util.RedisConfigTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("jscool")
public class JdHelperController {
    @Autowired
    private JdFruitService jdFruitService;
    @Autowired
    private JdPetService jdPetService;
    @Autowired
    private JdPlantbeanService jdPlantbeanService;
    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    RedisConfigTest RedisConfigTest;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @RequestMapping(value = "/jscool/{type}/{subscriptionurl}", method = {RequestMethod.GET})
    public void selectOne(@PathVariable String type, @PathVariable String subscriptionurl) throws Exception {
    /*    switch (type){
            case "fruit":
        }*/
        if("fruit".equals(type)||"plantbean".equals(type)||"pet".equals(type)){
            Map<String, Object> properties = new HashMap<>();
            properties.put("md5", subscriptionurl);
            properties.put("send_time", simpleDateFormat.format(new Date()));
            rabbitSender.send(type, properties);
        }

        //RedisConfigTest.testObj();
 /*       RedisConfigTest.HashOperations();
        RedisConfigTest.ListOperations();
        RedisConfigTest.testSetOperation();
        RedisConfigTest.testValueOption();*/
        BigInteger m = new BigInteger(String.valueOf(21));

    }

    @GetMapping("/healthz")
    public Boolean healthz() {
        return true;
    }
}
