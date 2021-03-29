package com.demo.ai.task;

import com.demo.ai.entity.JdHelp;
import com.demo.ai.entity.JdHelpUrl;
import com.demo.ai.entity.JdHelp;
import com.demo.ai.service.*;
import com.demo.ai.util.RestTemplateUtils;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JDTask {
    @Autowired
    private JdMobilecityService JdHelp;
    @Autowired
    private JdPetService jdPetService;
    @Autowired
    private JdPlantbeanService jdPlantbeanService;
    @Autowired
    private RestTemplateUtils httpAsyncExecutor;
    @Autowired
    private JdHelpService jdHelp;
    @Autowired
    JdHelpUrlService jdHelpUrlService;
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    public static Date localDate2Date(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /*    @Scheduled(initialDelay=1000, fixedDelay = 2000)
        public void task1() {
            System.out.println("延迟1000毫秒后执行，任务执行完2000毫秒之后执行！");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
/*    @Scheduled(fixedRate = 2000)
    public void task2() {
        System.out.println("延迟1000毫秒后执行，之后每2000毫秒执行一次！");
    }*/
    public static JavaType getCollectionType(ObjectMapper obj, Class<?> collectionClass, Class<?>... elementClasses) {
        return obj.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
    // @Scheduled(cron = "* 22 23 * * ?")
    //@Scheduled(cron = "0 25 8 1,10,20 * ?")
    public void updataUserTodaystatus() throws InterruptedException {
        System.out.println("进入发送助理码模块");
        Map<String,String> urlMap = new HashMap<>();
        Long start= System.currentTimeMillis();
        ObjectMapper obj = new ObjectMapper();
        JdHelpUrl jdHelpUrl = new JdHelpUrl();
        jdHelpUrl.setStatus("1");
        List<JdHelpUrl>   jdHelpUrlList =   jdHelpUrlService.queryAll(jdHelpUrl);
        for(JdHelpUrl jd:  jdHelpUrlList){
            urlMap.put(jd.getTaskType(),jd.getUrl());
        }
        JdHelp dFruit = new JdHelp();
        dFruit.setUserStatus("1");
        //   dFruit.setCreateTime(localDate2Date(LocalDate.now().plusDays(-1)));
        //  dFruit.setUpdateTime(localDate2Date(LocalDate.now()));
        dFruit.setUserTodaystatus("1");
        dFruit.setTodayEffectcount(null);
        dFruit.setTodaycount(null);
        dFruit.setCount(null);
        List<JdHelp>   helpList = jdHelp.queryAll(dFruit);
       // Set<Serializable> setSerializable = redisTemplate.boundSetOps("selfmobile:").members();
        for(JdHelp jdh : helpList){
            try {
                sendCode(urlMap,jdh);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Thread.sleep(40000); //由类名调用
                try {
                    sendCode(urlMap,jdh);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    Thread.sleep(10000); //由类名调用
                    try {
                        sendCode(urlMap,jdh);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Thread.sleep(2000); //由类名调用
                        try {
                            sendCode(urlMap,jdh);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }

            }

        }

        System.out.println("发送助理码完毕:"+(System.currentTimeMillis()-start));

    }

    private void sendCode(Map urlMap,JdHelp jdh ) throws Exception {
        Random rand = new Random();
        int MAX=10;//3-10秒间的随机数作为延迟时间
        int MIN=3;
        int randNumber = rand.nextInt(MAX - MIN + 1) + MIN;
        //Thread.sleep(randNumber*1000); //由类名调用

        HttpHeaders headers = new HttpHeaders();
        headers.add("UserAgent","Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1\"");
        headers.add("x-forwarded-for",jdh.getIp());
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        String urlTemplate = urlMap.get(jdh.getTaskType())+jdh.getUserMd5();
        ResponseEntity<String> responseEntity = httpAsyncExecutor.exchange(urlTemplate, HttpMethod.GET, requestEntity, String.class);
        if (200 ==responseEntity.getStatusCodeValue()) {
            System.out.println("添加结果"+responseEntity.getBody());
        } else {
            System.out.println(("#method# 远程调用失败 httpCode = [{}]"+responseEntity.getStatusCode()));
        }
}
    /**
     * shanchu
     */
   // @Scheduled(cron = "0 50 23 * * ?")
    public void updataSend() {
        System.out.println("进入删除助理码模块");
        redisTemplate.delete("selfmobile:");
        JdHelp.deleteAll();
        // deleteByPrex("mobile:");

    }

    //  @Scheduled(cron = "* 47 18 * * ?")
    //  @Scheduled(cron = "* 27 19 * * ?")
    //  @Scheduled(cron = "* 10 16 * * ?")
    //  @Scheduled(cron = "* 26 23 * * ?")
    @Scheduled(cron = "0 15,44 8,9,10,11,13,15,18,20,21,22,23,5 * * ?")
    public void taskQueryMysql() {
        System.out.println("进入生成助理码");
       // deleteByPrex("mobile:");
        //System.out.println("shanchu");
        //4次需要弄5个人
        JdHelp dFruit = new JdHelp();
        dFruit.setUserStatus("1");
        //   dFruit.setCreateTime(localDate2Date(LocalDate.now().plusDays(-1)));
        //  dFruit.setUpdateTime(localDate2Date(LocalDate.now()));
        dFruit.setUserTodaystatus("1");
        dFruit.setTodayEffectcount(null);
        dFruit.setTodaycount(null);
        dFruit.setCount(null);
        List<JdHelp>   helpList = jdHelp.queryAll(dFruit);
        JdHelpUrl jdHelpUrl = new JdHelpUrl();
        jdHelpUrl.setStatus("1");
        List<JdHelpUrl>   jdHelpUrlList =   jdHelpUrlService.queryAll(jdHelpUrl);

        for(JdHelpUrl jdurl :jdHelpUrlList){
            List<JdHelp>  newJdHelp  =   helpList.stream().filter(a->a.getTaskType().equals(jdurl.getTaskType())).collect(Collectors.toList());
            int i = 0;
            Set<JdHelp> jdFruitSet = new HashSet<>();
            for (JdHelp jf : newJdHelp) {
                if (i < jdurl.getHelpCount()) {
                    jdFruitSet.add(jf);

                } else {
                    //todo
                    for (JdHelp kjfset : jdFruitSet) {
                        for (JdHelp jfset : jdFruitSet) {
                            if (!kjfset.getUserMd5().equals(jfset.getUserMd5())) {
                                redisTemplate.opsForSet().add(jdurl.getTaskType()+":" + kjfset.getUserMd5(), jfset);
                            }
                        }
                    }
                    jdFruitSet.clear();
                    i = 0;
                    jdFruitSet.add(jf);
                }
                i++;
            }
            for (JdHelp kjfset : jdFruitSet) {
                for (JdHelp jfset : jdFruitSet) {
                    if (!kjfset.getUserMd5().equals(jfset.getUserMd5())) {
                        redisTemplate.opsForSet().add(jdurl.getTaskType()+":" + kjfset.getUserMd5(), jfset);
                    }
                }
            }

            //查出全部符合数据
            //分组
            //分别插入redis
            //没更新掉的继续更新redis,可以使用set对象作为存储就不会重复了
            System.out.println("更新状态！"+jdurl.getTaskType());
        }





/*        //查询数据更新数据状态
        for (JdHelp jf : jdFruitList) {
            //
            Set<JdHelp> fruitSet = redisTemplate.boundSetOps("mobile:"+jf.getUserMd5()).members();
            if (fruitSet.size() == 3) {
                for (JdHelp jdf : fruitSet) {
                    jdf.setUserStatus("0");
                    jdFruitService.update(jdf);
                }
            }
        }
        //查询数据更新数据状态
        for (JdPet jf : jdPetList) {
            //
            Set<JdPet> fruitSet = redisTemplate.boundSetOps("mobile:"+jf.getUserMd5()).members();
            if (fruitSet.size() == 5) {
                for (JdPet jdf : fruitSet) {
                    jdf.setUserStatus("0");
                    jdPetService.update(jdf);
                }
            }
        }
        //查询数据更新数据状态
        for (JdPlantbean jf : jdPlantbeanList) {
            //
            Set<JdPlantbean> fruitSet = redisTemplate.boundSetOps("mobile:"+jf.getUserMd5()).members();
            if (fruitSet.size() == 2) {
                for (JdPlantbean jdf : fruitSet) {
                    jdf.setUserStatus("0");
                    jdPlantbeanService.update(jdf);
                }
            }
        }*/

    }

    //合理的把数据库状态更新掉
    //@Scheduled(cron = "* 46 22 * * ?")
    public void delByPrex() {
        //最后一定要带上 *
        Set<String> keys = redisTemplate.keys("user*");
        if (CollectionUtils.isNotEmpty(keys)) {
            redisTemplate.delete(keys);
            System.out.println("jjj");
        }
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
