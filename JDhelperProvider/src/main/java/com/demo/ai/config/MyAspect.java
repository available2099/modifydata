package com.demo.ai.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

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

        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            logger.info("url={}",request.getRequestURI());
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
        //logger.info("aop doAfter");
    }
}