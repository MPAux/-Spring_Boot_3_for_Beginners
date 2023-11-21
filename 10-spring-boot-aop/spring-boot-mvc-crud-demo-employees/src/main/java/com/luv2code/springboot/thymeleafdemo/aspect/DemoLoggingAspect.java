package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    //setup logger
    private Logger myLogger = Logger.getLogger(this.getClass().getName());
    private int count = 0;

    //setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage() {};

    //do the same for service and dao
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage() {};

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage() {};

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {};

    // add  @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {
        count++;
        // display method we are calling
        myLogger.info(count + "=====> in @Before: calling method " + theJoinPoint.getSignature().toShortString());

        // display the arguments to the method

        // get the arguments
        List<Object> args = Arrays.asList(theJoinPoint.getArgs());
        // curiosity: (was a java.util.Arrays$ArrayList) myLogger.info("args list class: "+args.getClass().getName());

        // loop thru and display args

        myLogger.info("args:");
        if(args.isEmpty()) {
            myLogger.info("**no args**");
        } else {
            for (Object arg : args) {
                myLogger.info(arg.toString());
            }
        }
        myLogger.info("----------");

    }

    // add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void after(JoinPoint theJoinPoint, Object result) {
        myLogger.info("=====> in @AfterReturning: "+theJoinPoint.getSignature().toShortString()+", result: " + result.toString());
    }
}
