package com.demo.ai.config;

import com.demo.ai.util.GlobalConstants;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@Aspect
@Slf4j
//@DependsOn("requestContextListener")
@Component
public class MyAspect {
    private final static Logger logger = LoggerFactory.getLogger(MyAspect.class);

    //这个切点的表达式需要根据自己的项目来写
    @Pointcut("execution(public * com.demo.ai.controller..*(..))")
    public void log() {

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        //logger.info("aop doBefore..");
        System.out.println("线程2："+Thread.currentThread().getName());

        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            logger.info("url={}",request.getRequestURI());
            Object[] args = joinPoint.getArgs();
            String strings = "";
            for (Object arg : args) {
                strings =strings+"参数：" + arg;
            }
            System.out.println("请求参数：" + strings);
            Map<String, String> map = new LinkedHashMap<>();
            map.put("url",request.getRequestURI());
            map.put("args",strings);
            GlobalConstants.threadLocalUser.set(map);
            //logger.info("method={}", request.getMethod());
            //logger.info("ip={}", request.getRemoteAddr());
            //logger.info("classMethod={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
           /* Enumeration<String> paramter = request.getParameterNames();
            while (paramter.hasMoreElements()) {
                String str = (String) paramter.nextElement();
                logger.info(str + "={}", request.getParameter(str));
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After("log()")
    public void doAfter() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //logger.info("aop doAfter");
    }
    @AfterReturning(value = "log()", returning="returnValue")
    public void doAfterReturning(JoinPoint jp, Object returnValue){
        Map<String, String> map = GlobalConstants.threadLocalUser.get();
        System.out.println("线程2："+Thread.currentThread().getName());

        //  System.out.println("进入后置增强了！");
        String name = jp.getSignature().getName();
        System.out.println("方法名："+name);
/*        Object[] args = jp.getArgs();
        for (Object arg : args) {
            System.out.println("参数：" + arg);
        }*/
        System.out.println("方法返回值为：" + returnValue);
        GlobalConstants.threadLocalUser.remove();
    }

}