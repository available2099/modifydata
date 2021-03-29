package com.demo.ai.service.impl;

import com.demo.ai.dao.JdFruitDao;
import com.demo.ai.dao.JdPetDao;
import com.demo.ai.entity.JdFruit;
import com.demo.ai.entity.JdPet;
import com.demo.ai.service.InsertDateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class InsertDateServiceImpl implements InsertDateService {
    @Resource
    private JdPetDao jdPetDao;
    @Resource
    private JdFruitDao jdFruitDao;
    @Transactional
    @Override
    public void insertFruit() {
        JdFruit jdFruit = new JdFruit();
        jdFruit.setUserMd5("6768763yggg");
        jdFruit.setUserStatus("1");
        jdFruit.setUniqueId("spring_returned_message_correlation");
        jdFruit.setUserTodaystatus("1");
        jdFruit.setTodaycount(1);
        jdFruit.setUpdateTime(new Date());
        jdFruit.setCreateTime(new Date());
        jdFruitDao.insert(jdFruit);
        insertPet();
    }
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NESTED)
    @Override
    public void insertPet() {

        JdPet jdPet = new JdPet();
        jdPet.setUserMd5("jiwjijijio");
        jdPet.setUserStatus("1");
        jdPet.setUniqueId("spring_returned_message_correlation");
        jdPet.setUserTodaystatus("1");
        jdPet.setTodaycount(1);
        jdPet.setUpdateTime(new Date());
        jdPet.setCreateTime(new Date());
        jdPetDao.insert(jdPet);
        String aa=null;
        if(aa.length()>0){
            System.out.println("OKOKO");
        }
    }
}
