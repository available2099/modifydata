package com.demo.ai.task;

import com.demo.ai.entity.JdFruit;
import com.demo.ai.entity.JdPet;
import com.demo.ai.entity.JdPlantbean;
import com.demo.ai.service.JdFruitService;
import com.demo.ai.service.JdPetService;
import com.demo.ai.service.JdPlantbeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UpdateMysqlTask {
    @Autowired
    private JdFruitService jdFruitService;
    @Autowired
    private JdPetService jdPetService;
    @Autowired
    private JdPlantbeanService jdPlantbeanService;
    @Resource
    private RedisTemplate redisTemplate;
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
   // @Scheduled(cron = "* 44 */1 * * ?")
    public void taskQueryMysql() {
        //4次需要弄5个人
        JdFruit dFruit =new JdFruit();
        dFruit.setCreateTime(null);
        dFruit.setUpdateTime(null);
        dFruit.setUserTodaystatus(null);
        dFruit.setTodayEffectcount(null);
        dFruit.setTodaycount(null);
        dFruit.setCount(null);
        dFruit.setUserStatus("1");
        //5次需要6个人
        JdPet dPet = new JdPet();
        dPet.setCreateTime(null);
        dPet.setUpdateTime(null);
        dPet.setUserTodaystatus(null);
        dPet.setTodayEffectcount(null);
        dPet.setTodaycount(null);
        dPet.setCount(null);
        dPet.setUserStatus("1");

        //3次需要4个人
        JdPlantbean dPlantbean = new JdPlantbean();
        dPlantbean.setCreateTime(null);
        dPlantbean.setUpdateTime(null);
        dPlantbean.setUserTodaystatus(null);
        dPlantbean.setTodayEffectcount(null);
        dPlantbean.setTodaycount(null);
        dPlantbean.setCount(null);
        dPlantbean.setUserStatus("1");

        List<JdFruit> jdFruitList= jdFruitService.queryAll(dFruit);
        List<JdPet>  jdPetList = jdPetService.queryAll(dPet);
        List<JdPlantbean> jdPlantbeanList = jdPlantbeanService.queryAll(dPlantbean);

        //查询数据更新数据状态
        for (JdFruit jf : jdFruitList){
          //
            Set<JdFruit>  fruitSet = redisTemplate.boundSetOps(jf.getUserMd5()).members();
            if(fruitSet.size()==3){
                for(JdFruit jdf: fruitSet){
                    jdf.setUserStatus("0");
                    jdFruitService.update(jdf);
                }
            }
        }
        //查询数据更新数据状态
        for (JdPet jf : jdPetList){
            //
            Set<JdPet>  fruitSet = redisTemplate.boundSetOps(jf.getUserMd5()).members();
            if(fruitSet.size()==4){
                for(JdPet jdf: fruitSet){
                    jdf.setUserStatus("0");
                    jdPetService.update(jdf);
                }
            }
        }
        //查询数据更新数据状态
        for (JdPlantbean jf : jdPlantbeanList){
            //
            Set<JdPlantbean>  fruitSet = redisTemplate.boundSetOps(jf.getUserMd5()).members();
            if(fruitSet.size()==2){
                for(JdPlantbean jdf: fruitSet){
                    jdf.setUserStatus("0");
                    jdPlantbeanService.update(jdf);
                }
            }
        }

        //查出全部符合数据
        //分组
        //分别插入redis
        //没更新掉的继续更新redis,可以使用set对象作为存储就不会重复了
        System.out.println("每1小时执行一次！");

    }
    //合理的把数据库状态更新掉

}