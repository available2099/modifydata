package com.demo.ai.controller;

import com.demo.ai.entity.JdFruit;
import com.demo.ai.entity.JdPet;
import com.demo.ai.entity.JdPlantbean;
import com.demo.ai.producer.RabbitSender;
import com.demo.ai.service.JdFruitService;
import com.demo.ai.service.JdPetService;
import com.demo.ai.service.JdPlantbeanService;
import com.demo.ai.util.RedisConfigTest;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static JavaType getCollectionType(ObjectMapper obj,Class<?> collectionClass, Class<?>... elementClasses) {
        return obj.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    @RequestMapping(value = "/jscool/{type}/{subscriptionurl}", method = {RequestMethod.GET})
    public String selectOne(@PathVariable String type, @PathVariable String subscriptionurl) throws Exception {

        if ("fruit".equals(type) || "plantbean".equals(type) || "pet".equals(type)) {
            Map<String, Object> properties = new HashMap<>();
            properties.put("md5", subscriptionurl);
            properties.put("send_time", simpleDateFormat.format(new Date()));
            rabbitSender.send(type, properties);
        }
        ObjectMapper obj = new ObjectMapper();

        String md5 = "";
        try {
            switch (type) {
                case "fruit":
                    Set<Serializable> fruitSetSerializable = redisTemplate.boundSetOps("user:"+subscriptionurl).members();
                    md5 = "760517d4be0b4082a5c6cf5529e4599e";
                    String json = obj.writeValueAsString(fruitSetSerializable);
                    JavaType javaType = getCollectionType(obj,Set.class, JdFruit.class);

                    Set<JdFruit> fruitSet=   obj.readValue(json,javaType);
                    for (JdFruit fr : fruitSet) {
                        if(md5.equals(subscriptionurl)){
                            md5=fr.getUserMd5();
                        }else {
                            md5 = md5 + "@" + fr.getUserMd5();
                        }
                    }
                    break;
                case "pet":
                    Set<Serializable> petSetSerializable = redisTemplate.boundSetOps("user:"+subscriptionurl).members();
                    md5 = "";
                    String jsonJdPet = obj.writeValueAsString(petSetSerializable);
                    JavaType javaTypeJdPet = getCollectionType(obj,Set.class, JdPet.class);

                    Set<JdPet> petSet=   obj.readValue(jsonJdPet,javaTypeJdPet);
                    for (JdPet fr : petSet) {
                        if("".equals(md5)){
                            md5 = fr.getUserMd5();
                        }else {
                            md5 = md5 + "@" + fr.getUserMd5();
                        }
                    }
                    break;
                case "plantbean":
                    Set<Serializable> PlantbeanSetSerializable =  redisTemplate.boundSetOps("user:"+subscriptionurl).members();
                    md5 = "fnfkmp5hx2byrqss7h5jr5j2wtnlfimruj4z7ii";
                    String jsonJdPlantbean = obj.writeValueAsString(PlantbeanSetSerializable);
                    JavaType javaTypeJdPlantbean = getCollectionType(obj,Set.class, JdPlantbean.class);

                    Set<JdPlantbean> PlantbeanSet=   obj.readValue(jsonJdPlantbean,javaTypeJdPlantbean);
                    for (JdPlantbean fr : PlantbeanSet) {
                        if(md5.equals(subscriptionurl)){
                            md5=fr.getUserMd5();
                        }else {
                            md5 = md5 + "@" + fr.getUserMd5();
                        }
                    }
                    break;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }


        //RedisConfigTest.testObj();
 /*       RedisConfigTest.HashOperations();
        RedisConfigTest.ListOperations();
        RedisConfigTest.testSetOperation();
        RedisConfigTest.testValueOption();*/
     //   BigInteger m = new BigInteger(String.valueOf(21));
        return md5;
    }

    @GetMapping("/healthz")
    public Boolean healthz() {
        return true;
    }
}
