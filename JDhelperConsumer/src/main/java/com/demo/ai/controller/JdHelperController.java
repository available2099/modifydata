package com.demo.ai.controller;

import com.demo.ai.service.JdFruitService;
import com.demo.ai.service.JdPetService;
import com.demo.ai.service.JdPlantbeanService;
import com.demo.ai.util.RedisConfigTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    RedisConfigTest RedisConfigTest;

 /*   @RequestMapping(value = "/jscool/{type}/{subscriptionurl}", method = {RequestMethod.GET})
    public JdFruit selectOne(@PathVariable String type, @PathVariable String subscriptionurl) throws Exception {
        *//*switch (type){
            case "fruit":
        }*//*
        RedisConfigTest.testObj();
 *//*       RedisConfigTest.HashOperations();
        RedisConfigTest.ListOperations();
        RedisConfigTest.testSetOperation();
        RedisConfigTest.testValueOption();*//*
        BigInteger m = new BigInteger(String.valueOf(21));
        return this.jdFruitService.queryById(m);
    }*/

    @GetMapping("/healthz")
    public Boolean healthz() {
        return true;
    }
}
