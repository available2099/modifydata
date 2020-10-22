package com.demo.ai.task;

import com.demo.ai.entity.JdMobilecity;
import com.demo.ai.service.JdMobilecityService;
import com.demo.ai.service.JdPetService;
import com.demo.ai.service.JdPlantbeanService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MobileTask {
    @Autowired
    private JdMobilecityService jdMobilecity;
    @Autowired
    private JdPetService jdPetService;
    @Autowired
    private JdPlantbeanService jdPlantbeanService;

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
    // @Scheduled(cron = "* 22 23 * * ?")
    @Scheduled(cron = "* 22 22 * * ?")
    public void updataUserTodaystatus() {
        jdMobilecity.deleteAll();
       // deleteByPrex("mobile:");

    }

    //  @Scheduled(cron = "* 47 18 * * ?")
    //  @Scheduled(cron = "* 27 19 * * ?")
    //  @Scheduled(cron = "* 10 16 * * ?")

    @Scheduled(cron = "* 15 */1 * * ?")
    public void taskQueryMysql() {
        deleteByPrex("mobile:");
        System.out.println("shanchu");
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
            if (i < 11) {
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

/*        //查询数据更新数据状态
        for (JdMobilecity jf : jdFruitList) {
            //
            Set<JdMobilecity> fruitSet = redisTemplate.boundSetOps("mobile:"+jf.getUserMd5()).members();
            if (fruitSet.size() == 3) {
                for (JdMobilecity jdf : fruitSet) {
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
