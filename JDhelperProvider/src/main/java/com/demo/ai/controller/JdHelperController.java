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
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

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
    @Qualifier("user.scheduler")
    private ExecutorService userScheduler;
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static JavaType getCollectionType(ObjectMapper obj, Class<?> collectionClass, Class<?>... elementClasses) {
        return obj.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    @RequestMapping(value = "/initSku", method = RequestMethod.GET)
    public String initSku() {
        //初始化库存数量
        redisTemplate.opsForValue().set("numfruit", 5);
        redisTemplate.opsForValue().set("numplantbean", 9);
        redisTemplate.opsForValue().set("numpet", 0);
        //初始化实际卖出的商品数量0
        return "初始化库存成功";
    }

    @RequestMapping(value = "/{type}/{subscriptionurl}", method = {RequestMethod.GET})
    public String queryData(@PathVariable String type, @PathVariable String subscriptionurl, HttpServletRequest request, @RequestParam("ti") String time) throws Exception {
        ObjectMapper obj = new ObjectMapper();
        String userAgent = request.getHeader("user-agent");
        System.out.println("客户端请求类型："+userAgent);
        //计算时间差
        Long useTime = System.currentTimeMillis() - Long.parseLong(time);
        System.out.println("接口请求时间：" + useTime);
        System.out.println("time is :" + time);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(Long.parseLong(time)));
        // 将时间戳转为当前时间
        // LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(Long.parseLong(time), 0, ZoneOffset.ofHours(8));
        // 2020-02-03T13:30:44
        System.out.println("请求时间" + date);


        //判断长度
        if (userAgent.contains("Quantumult") && subscriptionurl.length() > 20) {
            String ip = getIpAddress(request);
            System.out.println("ip是多少：" + ip);
            //查询一下redis是否有数据，有的话返回空
            if (redisTemplate.opsForValue().get("time:" + subscriptionurl) == null) {

                String[] md5arry = new String[]{"760517d4be0b4082a5c6cf5529e4599e","fnfkmp5hx2byrqss7h5jr5j2wtnlfimruj4z7ii"};
                String md5 = "";
                if(!ArrayUtils.contains(md5arry, subscriptionurl)){
                    redisTemplate.setEnableTransactionSupport(true);//开启事务的支持
                    redisTemplate.watch("num" + type);//watch某个key,当该key被其它客户端改变时,则会中断当前的操作

                    String numTemp = obj.writeValueAsString(redisTemplate.opsForValue().get("num" + type));

                    long num = Long.valueOf(numTemp);//获取当前商品的数量
                    if (num <= 0) {//检查当前商品的数量
                        System.out.println("----"+type+"----秒杀已结束");
                    } else {
                        redisTemplate.multi();//事务
                        redisTemplate.boundValueOps("num" + type).decrement(1);//下单成功 商品数量减1
                        List<Object> exec = redisTemplate.exec();//执行事务
                        if (exec == null || exec.size() == 0) {
                            System.out.println("----"+type+"----秒杀失败");
                        } else {
                            System.out.println("----"+type+"----秒杀成功");
                            switch (type) {
                                case "fruit": md5 = "760517d4be0b4082a5c6cf5529e4599e";
                                    break;
                                case "pet": md5 ="";
                                    break;
                                case "plantbean":md5 = "fnfkmp5hx2byrqss7h5jr5j2wtnlfimruj4z7ii";
                                    break;
                            }
                        }
                    }

                }


              redisTemplate.opsForValue().set("time:" + subscriptionurl, ip, 59, TimeUnit.MINUTES);

                if ("fruit".equals(type) || "plantbean".equals(type) || "pet".equals(type)) {
                    Map<String, Object> properties = new HashMap<>();
                    properties.put("md5", subscriptionurl);
                    properties.put("send_time", simpleDateFormat.format(new Date()));
                    userScheduler.execute(() -> {
                                try {
                                    rabbitSender.send(type, properties);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                    );
                }
                System.out.println("doreturndata");

                try {
                    switch (type) {
                        case "fruit":
                            Set<Serializable> fruitSetSerializable = redisTemplate.boundSetOps("user:" + subscriptionurl).members();
                            String json = obj.writeValueAsString(fruitSetSerializable);
                            JavaType javaType = getCollectionType(obj, Set.class, JdFruit.class);

                            Set<JdFruit> fruitSet = obj.readValue(json, javaType);
                            for (JdFruit fr : fruitSet) {
                                if ("".equals(md5)) {
                                    md5 = fr.getUserMd5();
                                } else {
                                    md5 = md5 + "@" + fr.getUserMd5();
                                }
                            }
                            break;
                        case "pet":
                            Set<Serializable> petSetSerializable = redisTemplate.boundSetOps("user:" + subscriptionurl).members();
                            String jsonJdPet = obj.writeValueAsString(petSetSerializable);
                            JavaType javaTypeJdPet = getCollectionType(obj, Set.class, JdPet.class);

                            Set<JdPet> petSet = obj.readValue(jsonJdPet, javaTypeJdPet);
                            for (JdPet fr : petSet) {
                                if ("".equals(md5)) {
                                    md5 = fr.getUserMd5();
                                } else {
                                    md5 = md5 + "@" + fr.getUserMd5();
                                }
                            }
                            break;
                        case "plantbean":
                            Set<Serializable> PlantbeanSetSerializable = redisTemplate.boundSetOps("user:" + subscriptionurl).members();
                            String jsonJdPlantbean = obj.writeValueAsString(PlantbeanSetSerializable);
                            JavaType javaTypeJdPlantbean = getCollectionType(obj, Set.class, JdPlantbean.class);

                            Set<JdPlantbean> PlantbeanSet = obj.readValue(jsonJdPlantbean, javaTypeJdPlantbean);
                            for (JdPlantbean fr : PlantbeanSet) {
                                if ("".equals(md5)) {
                                    md5 = fr.getUserMd5();
                                } else {
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
        }
        return "";
    }


    @RequestMapping(value = "/jscool/{type}/{subscriptionurl}", method = {RequestMethod.GET})
    public String selectOne(@PathVariable String type, @PathVariable String subscriptionurl, HttpServletRequest request) throws Exception {
        String userAgent = request.getHeader("user-agent");
        System.out.println("客户端请求类型："+userAgent);
        //判断长度
        ObjectMapper obj = new ObjectMapper();
        if (userAgent.contains("Quantumult") && subscriptionurl.length() > 20) {
            String ip = getIpAddress(request);
            System.out.println("ip是多少：" + ip);
            //查询一下redis是否有数据，有的话返回空
            if (redisTemplate.opsForValue().get("time:" + subscriptionurl) == null) {
                String[] md5arry = new String[]{"760517d4be0b4082a5c6cf5529e4599e", "fnfkmp5hx2byrqss7h5jr5j2wtnlfimruj4z7ii"};
                String md5 = "";
                if (!ArrayUtils.contains(md5arry, subscriptionurl)) {
                    redisTemplate.setEnableTransactionSupport(true);//开启事务的支持
                    redisTemplate.watch("num" + type);//watch某个key,当该key被其它客户端改变时,则会中断当前的操作

                    String numTemp = obj.writeValueAsString(redisTemplate.opsForValue().get("num" + type));

                    long num = Long.valueOf(numTemp);//获取当前商品的数量
                    if (num <= 0) {//检查当前商品的数量
                        System.out.println("----" + type + "----秒杀已结束");
                    } else {
                        redisTemplate.multi();//事务
                        redisTemplate.boundValueOps("num" + type).decrement(1);//下单成功 商品数量减1
                        List<Object> exec = redisTemplate.exec();//执行事务
                        if (exec == null || exec.size() == 0) {
                            System.out.println("----" + type + "----秒杀失败");
                        } else {
                            System.out.println("----" + type + "----秒杀成功");
                            switch (type) {
                                case "fruit":
                                    md5 = "760517d4be0b4082a5c6cf5529e4599e";
                                    break;
                                case "pet":
                                    md5 = "";
                                    break;
                                case "plantbean":
                                    md5 = "fnfkmp5hx2byrqss7h5jr5j2wtnlfimruj4z7ii";
                                    break;
                            }
                        }
                    }

                }


                redisTemplate.opsForValue().set("time:" + subscriptionurl, ip, 59, TimeUnit.MINUTES);

                if ("fruit".equals(type) || "plantbean".equals(type) || "pet".equals(type)) {
                    Map<String, Object> properties = new HashMap<>();
                    properties.put("md5", subscriptionurl);
                    properties.put("send_time", simpleDateFormat.format(new Date()));
                    userScheduler.execute(() -> {
                                try {
                                    rabbitSender.send(type, properties);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                    );
                }
                System.out.println("doreturndata");

                try {
                    switch (type) {
                        case "fruit":
                            Set<Serializable> fruitSetSerializable = redisTemplate.boundSetOps("user:" + subscriptionurl).members();
                            String json = obj.writeValueAsString(fruitSetSerializable);
                            JavaType javaType = getCollectionType(obj, Set.class, JdFruit.class);

                            Set<JdFruit> fruitSet = obj.readValue(json, javaType);
                            for (JdFruit fr : fruitSet) {
                                if ("".equals(md5)) {
                                    md5 = fr.getUserMd5();
                                } else {
                                    md5 = md5 + "@" + fr.getUserMd5();
                                }
                            }
                            break;
                        case "pet":
                            Set<Serializable> petSetSerializable = redisTemplate.boundSetOps("user:" + subscriptionurl).members();
                            String jsonJdPet = obj.writeValueAsString(petSetSerializable);
                            JavaType javaTypeJdPet = getCollectionType(obj, Set.class, JdPet.class);

                            Set<JdPet> petSet = obj.readValue(jsonJdPet, javaTypeJdPet);
                            for (JdPet fr : petSet) {
                                if ("".equals(md5)) {
                                    md5 = fr.getUserMd5();
                                } else {
                                    md5 = md5 + "@" + fr.getUserMd5();
                                }
                            }
                            break;
                        case "plantbean":
                            Set<Serializable> PlantbeanSetSerializable = redisTemplate.boundSetOps("user:" + subscriptionurl).members();
                            String jsonJdPlantbean = obj.writeValueAsString(PlantbeanSetSerializable);
                            JavaType javaTypeJdPlantbean = getCollectionType(obj, Set.class, JdPlantbean.class);

                            Set<JdPlantbean> PlantbeanSet = obj.readValue(jsonJdPlantbean, javaTypeJdPlantbean);
                            for (JdPlantbean fr : PlantbeanSet) {
                                if ("".equals(md5)) {
                                    md5 = fr.getUserMd5();
                                } else {
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
        }
        return "";
    }

    @GetMapping("/healthz")
    public Boolean healthz() {
        return true;
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
