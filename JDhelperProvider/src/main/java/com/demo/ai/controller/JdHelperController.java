package com.demo.ai.controller;

import com.demo.ai.dto.JdHelpDto;
import com.demo.ai.entity.*;
import com.demo.ai.producer.RabbitSender;
import com.demo.ai.service.*;
import com.demo.ai.service.impl.JdHelpServiceImpl;
import com.demo.ai.util.GlobalConstants;
import com.demo.ai.util.RedisConfigTest;
import com.demo.ai.util.SnowflakeIdWorker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private JdMobilecityService jdMobilecity;
    @Autowired
    private JdHelpService jdHelp;
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    @Autowired
    private RedissonClient redisson;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static JavaType getCollectionType(ObjectMapper obj, Class<?> collectionClass, Class<?>... elementClasses) {
        return obj.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
 /*   @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e) {
        System.out.println("未知异常！原因是:" + e);
        //return e.getMessage();
        return "";
    }*/
    @RequestMapping(value = "/initmobile", method = RequestMethod.GET)
    public String initmobile() {
        createData();
        return "初始化成功";
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

    public String secondkill(String type, String subscriptionurl) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        String[] md5arry = new String[]{"760517d4be0b4082a5c6cf5529e4599e", "fnfkmp5hx2byrqss7h5jr5j2wtnlfimruj4z7ii"};
        String md5 = "";
        if (!ArrayUtils.contains(md5arry, subscriptionurl)) {
            //  redisTemplate.setEnableTransactionSupport(true);//开启事务的支持
            //  redisTemplate.watch("num" + type);//watch某个key,当该key被其它客户端改变时,则会中断当前的操作
            //生成、获取锁

            String numTemp = obj.writeValueAsString(redisTemplate.opsForValue().get("num" + type));
            System.out.println("----" + type + "----剩余库存：" + numTemp);
           //String numTemp ="5";
            Integer num = Integer.valueOf(numTemp);//获取当前商品的数量
            if (num <= 0) {//检查当前商品的数量
                System.out.println("----" + type + "----秒杀已结束");
            } else {

                RLock lock = redisson.getLock(type+"hhhhhhh");

                try {

                    // 加锁
                    lock.lock(3,TimeUnit.MINUTES);
                    // long stock = redisTemplate.opsForValue().decrement("num" + type, 1);
                   // Integer stock = Integer.parseInt(obj.writeValueAsString(redisTemplate.opsForValue().get("num" + type)));
                    if(num > 0){
                        Integer realStock = num - 1;
                        redisTemplate.opsForValue().set("num" + type,realStock);
                        System.out.println("扣减成功，剩余库存" + realStock);
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
                    } else {
                        System.out.println("扣减失败，库存不足");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }  finally {
                    //释放锁
                        lock.unlock();

                }

                //    redisTemplate.multi();//事务
                //     redisTemplate.boundValueOps("num" + type).decrement(1);//下单成功 商品数量减1
                // List<Object> exec = redisTemplate.exec();//执行事务
    /*            if (exec == null || exec.size() == 0) {
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
                }*/
            }

        }
        return md5;
    }

    public String secondkill2(String type, String subscriptionurl) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        String[] md5arry = new String[]{"760517d4be0b4082a5c6cf5529e4599e", "fnfkmp5hx2byrqss7h5jr5j2wtnlfimruj4z7ii"};
        String md5 = "";
        if (!ArrayUtils.contains(md5arry, subscriptionurl)) {
            //  redisTemplate.setEnableTransactionSupport(true);//开启事务的支持
            //  redisTemplate.watch("num" + type);//watch某个key,当该key被其它客户端改变时,则会中断当前的操作

            String numTemp = obj.writeValueAsString(redisTemplate.opsForValue().get("num" + type));
            System.out.println("----" + type + "----剩余库存：" + numTemp);

            long num = Long.valueOf(numTemp);//获取当前商品的数量
            if (num <= 0) {//检查当前商品的数量
                System.out.println("----" + type + "----秒杀已结束");
            } else {
                long stock = redisTemplate.opsForValue().decrement("num" + type, 1);

                if (stock < 0) {
                    num = Long.valueOf(obj.writeValueAsString(redisTemplate.opsForValue().get("num" + type)));//获取当前商品的数量
                    if (num != 0 && num < 1) {
                        //返回redis库存
                        redisTemplate.opsForValue().increment("num" + type, 1);
                    } else if (num == 0) {
                        redisTemplate.opsForValue().set("num" + type, 0);
                    }
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

                //    redisTemplate.multi();//事务
                //     redisTemplate.boundValueOps("num" + type).decrement(1);//下单成功 商品数量减1
                // List<Object> exec = redisTemplate.exec();//执行事务
    /*            if (exec == null || exec.size() == 0) {
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
                }*/
            }

        }
        return md5;
    }

    ///api/v2/jd/5g/read/${randomCount}/create
    @RequestMapping(value = "/api/{v2}/jd/{type}/{opration}/{count}", method = {RequestMethod.GET})
    public ReturnDAO queryDataAPI(@PathVariable String v2, @PathVariable String type, @PathVariable String opration, @PathVariable String count, HttpServletRequest request) throws Exception {
        redisTemplate.boundValueOps("totalnew").increment(1);//计算请求量
        ObjectMapper obj = new ObjectMapper();
        String userAgent = request.getHeader("user-agent");
        System.out.println("客户端请求类型：" + userAgent);
        //计算时间差
        //判断长度 timeOrder.isBefore(timerequest) &&
        String ip = getIpAddress(request);
        System.out.println("ip是多少：" + ip);
        //查询一下redis是否有数据，有的话返回空
        String md5 = "";
        // if("create".equals(opration)){
        //新版本搞事情
        JdHelpService jdHelp = new JdHelpServiceImpl();
        userScheduler.execute(() -> {
                    //存数据到数据库
                    JdHelp jdFruit = new JdHelp();
                    jdFruit.setUserMd5(count);
                    jdFruit.setIp(ip);
                    jdFruit.setUserStatus("1");
                    jdFruit.setTaskType(type);
                    jdFruit.setUniqueId(SnowflakeIdWorker.generateId().toString());
                    jdFruit.setUserTodaystatus("1");
                    jdFruit.setTodaycount(1);
                    jdFruit.setUpdateTime(new Date());
                    jdFruit.setCreateTime(new Date());
                    jdHelp.insert(jdFruit);
                    //存数据到redis
                }
        );
        // }else if("read".equals(opration)){

        //redis查询数据

        Set<Serializable> fruitSetSerializable = redisTemplate.boundSetOps(type + ":" + count).members();
        String json = obj.writeValueAsString(fruitSetSerializable);
        JavaType javaType = getCollectionType(obj, Set.class, JdHelpDto.class);

        Set<JdHelpDto> fruitSet = obj.readValue(json, javaType);
             /*   if (!json.contains("760517d4be0b4082a5c6cf5529e4599e")) {
                    md5 = secondkill(type, subscriptionurl);
                }*/
        String newMd5 = "";
        List<String> md5List = new ArrayList<>();
        for (JdHelpDto fr : fruitSet) {
            md5List.add(fr.getUserMd5());
            if ("".equals(newMd5)) {
                newMd5 = fr.getUserMd5();

            } else {
                newMd5 = newMd5 + "@" + fr.getUserMd5();

            }
        }
        ReturnDAO returnDAO = new ReturnDAO();
        returnDAO.setCode(200);
        returnDAO.setMessage("success");
        returnDAO.setData(md5List.toArray(new String[0]));
        return returnDAO;
        // }

//return "";

    }

    @RequestMapping(value = "/{type}/{subscriptionurl}", method = {RequestMethod.GET})
    public String queryData(@PathVariable String type, @PathVariable String subscriptionurl, @RequestParam("ti") String time) throws Exception {
        System.out.println("线程1：" + Thread.currentThread().getName());
        redisTemplate.boundValueOps("totalnew").increment(1);//计算请求量
        ObjectMapper obj = new ObjectMapper();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String userAgent = request.getHeader("user-agent");
        System.out.println("客户端请求类型：" + userAgent);
        //计算时间差
        Long useTime = System.currentTimeMillis() - Long.parseLong(time);
        System.out.println("接口请求时间：" + useTime);
        //System.out.println("time is :" + time);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(Long.parseLong(time)));
        System.out.println("请求时间" + date);
        String datetime = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date(Long.parseLong(time)));
        String timere = "06:00:00";
        if ("fruit".equals(type)) {
            timere = "07:00:00";
        }
        LocalTime timerequest = LocalTime.parse(datetime, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime timeOrder = LocalTime.parse(timere, DateTimeFormatter.ofPattern("HH:mm:ss"));

        // 将时间戳转为当前时间
        // LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(Long.parseLong(time), 0, ZoneOffset.ofHours(8));
        // 2020-02-03T13:30:44

//        if (userAgent.contains("Quantumult") && subscriptionurl.length() > 20) {
        //判断长度 timeOrder.isBefore(timerequest) &&
        if (subscriptionurl.length() > 20) {
            String ip = getIpAddress(request);
            System.out.println("ip是多少：" + ip);
            Map<String, String> map = GlobalConstants.threadLocalUser.get();
            map.put("ip", ip);
            //查询一下redis是否有数据，有的话返回空
            if (redisTemplate.opsForValue().get("time:" + subscriptionurl) == null || "1".equals("1")) {

                //     String[] md5arry = new String[]{"760517d4be0b4082a5c6cf5529e4599e","fnfkmp5hx2byrqss7h5jr5j2wtnlfimruj4z7ii"};
                String md5 = "";
  /*              if(!ArrayUtils.contains(md5arry, subscriptionurl)){
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

                }*/


                redisTemplate.opsForValue().set("time:" + subscriptionurl, ip, 25, TimeUnit.MINUTES);
/*
                if ("fruit".equals(type) || "plantbean".equals(type) || "pet".equals(type)) {
                    Map<String, Object> properties = new HashMap<>();
                    properties.put("md5", subscriptionurl);
                    properties.put("ip", ip);
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
                            if (!json.contains("760517d4be0b4082a5c6cf5529e4599e")) {
                                md5 = secondkill(type, subscriptionurl);
                            }
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
                            if (!jsonJdPlantbean.contains("fnfkmp5hx2byrqss7h5jr5j2wtnlfimruj4z7ii")) {
                                md5 = secondkill(type, subscriptionurl);
                            }
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
                }*/


                //RedisConfigTest.testObj();
 /*       RedisConfigTest.HashOperations();
        RedisConfigTest.ListOperations();
        RedisConfigTest.testSetOperation();
        RedisConfigTest.testValueOption();*/
                //   BigInteger m = new BigInteger(String.valueOf(21));

                //新版本搞事情
                userScheduler.execute(() -> {
                            //存数据到数据库
                            JdHelp jdFruit = new JdHelp();
                            jdFruit.setUserMd5(subscriptionurl);
                            jdFruit.setIp(ip);
                            jdFruit.setUserStatus("1");
                            jdFruit.setTaskType(type);
                            jdFruit.setUniqueId(SnowflakeIdWorker.generateId().toString());
                            jdFruit.setUserTodaystatus("1");
                            jdFruit.setTodaycount(1);
                            jdFruit.setUpdateTime(new Date());
                            jdFruit.setCreateTime(new Date());
                            jdHelp.insert(jdFruit);
                            //存数据到redis
                        }
                );

                //redis查询数据

                Set<Serializable> fruitSetSerializable = redisTemplate.boundSetOps(type + ":" + subscriptionurl).members();
                String json = obj.writeValueAsString(fruitSetSerializable);
                JavaType javaType = getCollectionType(obj, Set.class, JdHelpDto.class);

                Set<JdHelpDto> fruitSet = obj.readValue(json, javaType);
             /*   if (!json.contains("760517d4be0b4082a5c6cf5529e4599e")) {

                }*/
                md5 = secondkill(type, subscriptionurl);
                String newMd5 = "";
                //  md5 = secondkill(type, subscriptionurl);
                for (JdHelpDto fr : fruitSet) {
                    if ("".equals(newMd5)) {
                        newMd5 = fr.getUserMd5();
                    } else {
                        newMd5 = newMd5 + "@" + fr.getUserMd5();
                    }
                }
                if (StringUtils.isNotBlank(newMd5)&&StringUtils.isNotBlank(md5)) {
                    return md5+"@"+newMd5;
                }else if(StringUtils.isNotBlank(newMd5)){
                    return newMd5;
                }
                return md5;
            }
        }
        return "";
    }



    @RequestMapping(value = "/mobile/{subscriptionurl}", method = {RequestMethod.GET})
    public String selectMobile(@PathVariable String subscriptionurl, HttpServletRequest request, @RequestParam("ti") String time) throws Exception {
        String md5 = "";
        String userAgent = request.getHeader("user-agent");
        System.out.println("客户端请求类型：" + userAgent);
        String ip = getIpAddress(request);
        System.out.println("ip是多少：" + ip);
        if (StringUtils.isNotBlank(time) && redisTemplate.opsForValue().get("timemobile:" + subscriptionurl) == null) {
            redisTemplate.opsForValue().set("timemobile:" + subscriptionurl, ip, 59, TimeUnit.MINUTES);

            //计算时间差
            Long useTime = System.currentTimeMillis() - Long.parseLong(time);
            System.out.println("接口请求时间：" + useTime);
            System.out.println("time is :" + time);
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(Long.parseLong(time)));
            System.out.println("mobile请求时间" + date);

            Map<String, Object> properties = new HashMap<>();
            properties.put("md5", subscriptionurl);
            properties.put("send_time", simpleDateFormat.format(new Date()));
            userScheduler.execute(() -> {
                        try {
                            rabbitSender.send("mobile", properties);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            );
            ObjectMapper obj = new ObjectMapper();


            Set<Serializable> fruitSetSerializable = redisTemplate.boundSetOps("mobile:" + subscriptionurl).members();
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

        /*    int[] items = new int[]{1, 2};

            Random rand = new Random();
         //   if (items[rand.nextInt(items.length)] == 1) {
                String selfcode = "";
                Set<Serializable> setSerializable = redisTemplate.boundSetOps("selfmobile:").members();
                String jsonJdPet = null;
                try {
                    jsonJdPet = obj.writeValueAsString(setSerializable);
                    JavaType javaTypeJdPet = getCollectionType(obj, Set.class, String.class);

                    Set<String> petSet = obj.readValue(jsonJdPet, javaTypeJdPet);
                    for (String ss : petSet) {
                        selfcode = ss;
                    if( items[rand.nextInt(items.length)] == 2){
                        break;
                    }
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                if (!"".equals(selfcode)) {
                    md5 = selfcode + "@" + md5;
                }*/
            // }

        }
        return md5;
    }

    @RequestMapping(value = "/selfmobile/{subscriptionurl}", method = {RequestMethod.GET})
    public String selfmobile(@PathVariable String subscriptionurl, HttpServletRequest request) throws Exception {
        redisTemplate.opsForSet().add("selfmobile:", subscriptionurl);

        Map<String, Object> properties = new HashMap<>();
        properties.put("md5", subscriptionurl);
        properties.put("send_time", simpleDateFormat.format(new Date()));
        userScheduler.execute(() -> {
                    try {
                        rabbitSender.send("mobile", properties);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        String md5 = "";
        ObjectMapper obj = new ObjectMapper();

        Set<Serializable> fruitSetSerializable = redisTemplate.boundSetOps("mobile:" + subscriptionurl).members();
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

        return md5;
    }

    @RequestMapping(value = "/jscool/{type}/{subscriptionurl}", method = {RequestMethod.GET})
    public String selectOne(@PathVariable String type, @PathVariable String subscriptionurl, HttpServletRequest request) throws Exception {
        String userAgent = request.getHeader("user-agent");
        System.out.println("客户端请求类型：" + userAgent);
        redisTemplate.boundValueOps("totalold").increment(1);

        //判断长度
        ObjectMapper obj = new ObjectMapper();
        //  if (userAgent.contains("Quantumult") && subscriptionurl.length() > 20) {
        if (subscriptionurl.length() > 20) {

            String ip = getIpAddress(request);
            System.out.println("ip是多少：" + ip);
            //查询一下redis是否有数据，有的话返回空
            if (redisTemplate.opsForValue().get("time:" + subscriptionurl) == null) {
                //     String[] md5arry = new String[]{"760517d4be0b4082a5c6cf5529e4599e", "fnfkmp5hx2byrqss7h5jr5j2wtnlfimruj4z7ii"};
                String md5 = "";
                /*if (!ArrayUtils.contains(md5arry, subscriptionurl)) {
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

                }*/


                redisTemplate.opsForValue().set("time:" + subscriptionurl, ip, 25, TimeUnit.MINUTES);

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
                            if (!json.contains("760517d4be0b4082a5c6cf5529e4599e")) {
                                md5 = secondkill(type, subscriptionurl);
                            }
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
                            if (!jsonJdPlantbean.contains("fnfkmp5hx2byrqss7h5jr5j2wtnlfimruj4z7ii")) {
                                md5 = secondkill(type, subscriptionurl);
                            }
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

    public void createData() {
        //4次需要弄5个人
        JdMobilecity dFruit = new JdMobilecity();
        dFruit.setUserStatus("1");
        //   dFruit.setCreateTime(localDate2Date(LocalDate.now().plusDays(-1)));
        //  dFruit.setUpdateTime(localDate2Date(LocalDate.now()));
        dFruit.setUserTodaystatus("1");
        dFruit.setTodayEffectcount(null);
        dFruit.setTodaycount(null);
        dFruit.setCount(null);

        List<JdMobilecity> jdFruitList = jdMobilecity.queryAll(dFruit);

        //保存一份历史记录
        //"fruit".equals(type) || "plantbean".equals(type) || "pet".equals(type)

      /*  redisTemplate.opsForZSet().add("hisfruit:",   (Serializable) jdFruitList,1);
        redisTemplate.opsForZSet().add("hisplantbean:", (Serializable)jdPlantbeanList,1);
        redisTemplate.opsForZSet().add("hispet:",(Serializable)jdPetList,1);
*/

        int i = 0;
        Set<JdMobilecity> jdFruitSet = new HashSet<>();
        for (JdMobilecity jf : jdFruitList) {
            if (i < 10) {
                jdFruitSet.add(jf);

            } else {
                //todo
                for (JdMobilecity kjfset : jdFruitSet) {
                    for (JdMobilecity jfset : jdFruitSet) {
                        if (!kjfset.getUserMd5().equals(jfset.getUserMd5())) {
                            redisTemplate.opsForSet().add("mobile:" + kjfset.getUserMd5(), jfset);
                        }
                    }
                }
                jdFruitSet.clear();
                i = 0;
                jdFruitSet.add(jf);
            }
            i++;
        }
        for (JdMobilecity kjfset : jdFruitSet) {
            for (JdMobilecity jfset : jdFruitSet) {
                if (!kjfset.getUserMd5().equals(jfset.getUserMd5())) {
                    redisTemplate.opsForSet().add("mobile:" + kjfset.getUserMd5(), jfset);
                }
            }
        }

        //查出全部符合数据
        //分组
        //分别插入redis
        //没更新掉的继续更新redis,可以使用set对象作为存储就不会重复了
        System.out.println("更新状态！");
    }

    /**
     * 根据前缀删除key
     *
     * @param prex
     */
    public void deleteByPrex(String prex) {
        //org.apache.commons.collections.CollectionUtils

        prex = prex + "*";
        Set<String> keys = redisTemplate.keys(prex);
        if (CollectionUtils.isNotEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }
}
