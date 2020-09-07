package com.demo.ai.util;

import com.demo.ai.entity.UserVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RedisConfigTest {


    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    RedisTemplate<String, String> stringRedisTemplate;


    public void testObj() throws Exception{
        UserVo userVo = new UserVo();
        userVo.setAddress("上海");
        userVo.setName("测试dfas");
        userVo.setAge(123);
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        //redisService.expireKey("name",20, TimeUnit.SECONDS);

        String key = RedisKeyUtil.getKey(UserVo.Table,"name",userVo.getName());
        operations.set(key, userVo);

        UserVo vo =  (UserVo)operations.get(key);

        String keylist = RedisKeyUtil.getKey(UserVo.Table,"namelist",userVo.getName());
List<UserVo> volist =new ArrayList<>();
        UserVo userVo2 = new UserVo();
        userVo2.setAddress("上海");
        userVo2.setName("测试dfas");
        userVo2.setAge(123);
        System.out.println(vo);
        redisTemplate.opsForList().leftPush(keylist,userVo2);
        redisTemplate.opsForList().leftPush(keylist,vo);
        List listKey1 = redisTemplate.boundListOps(keylist).range(0, 10);
        volist.addAll(listKey1);
        String keyzset = RedisKeyUtil.getKey(UserVo.Table,"namezset",userVo.getName());

        redisTemplate.opsForSet().add(keyzset,userVo);
        redisTemplate.opsForSet().add(keyzset,userVo);

        //redisTemplate.boundSetOps(keyzset);
        System.out.println(redisTemplate.boundSetOps(keyzset).members());
    }

/*
   public void testValueOption( )throws  Exception{
        UserVo userVo = new UserVo();
        userVo.setAddress("上海");
        userVo.setName("jantent");
        userVo.setAge(23);
        valueOperations.set("test",userVo);

        System.out.println(valueOperations.get("test"));
    }

    public void testSetOperation() throws Exception{
        UserVo userVo = new UserVo();
        userVo.setAddress("北京");
        userVo.setName("jantent");
        userVo.setAge(23);
        UserVo auserVo = new UserVo();
        auserVo.setAddress("n柜昂周");
        auserVo.setName("antent");
        auserVo.setAge(23);
        setOperations.add("user:test",userVo,auserVo);
        Set<Object> result = setOperations.members("user:test");
        System.out.println(result);
    }

    public void HashOperations() throws Exception{
        UserVo userVo = new UserVo();
        userVo.setAddress("北京");
        userVo.setName("jantent");
        userVo.setAge(23);
        hashOperations.put("hash:user",userVo.hashCode()+"",userVo);
        System.out.println(hashOperations.get("hash:user",userVo.hashCode()+""));
    }

    public void  ListOperations() throws Exception{
        UserVo userVo = new UserVo();
        userVo.setAddress("北京");
        userVo.setName("jantent");
        userVo.setAge(23);
//        listOperations.leftPush("list:user",userVo);
//        System.out.println(listOperations.leftPop("list:user"));
        // pop之后 值会消失
        System.out.println(listOperations.leftPop("list:user"));
    }*/
}