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
    public JdFruit selectOne(@PathVariable String type, @PathVariable String subscriptionurl) throws Exception {
        /*switch (type){
            case "fruit":
        }*/
        Map<String, Object> properties = new HashMap<>();
        properties.put("md5", "760517d4be0b4082a5c6cf5529e4599e");
        properties.put("send_time", simpleDateFormat.format(new Date()));
        rabbitSender.send("Hello RabbitMQ For Spring Boot!", properties);

        RedisConfigTest.testObj();
 /*       RedisConfigTest.HashOperations();
        RedisConfigTest.ListOperations();
        RedisConfigTest.testSetOperation();
        RedisConfigTest.testValueOption();*/
        BigInteger m = new BigInteger(String.valueOf(21));
        return this.jdFruitService.queryById(m);
    }

    @GetMapping("/healthz")
    public Boolean healthz() {
        return true;
    }
}
