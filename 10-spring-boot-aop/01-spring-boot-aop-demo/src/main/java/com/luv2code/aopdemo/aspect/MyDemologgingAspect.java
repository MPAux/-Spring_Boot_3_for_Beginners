package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemologgingAspect {
    // private final String myPointcut = "execution(* com.luv2code.aopdemo.dao.*.*(..))";
    // this is where we add all of our related advices for logging

    // let's start with an @Before advice

    // @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
    // @Before("execution(void add*())")
    // @Before("execution(* add*())")
    // @Before("execution(* add*(com.luv2code.aopdemo.Account))")
    // @Before("execution(* add*(*.*.*.Account))")
    // @Before("execution(* add*(com.luv2code.aopdemo.Account))")
    // @Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")
    // @Before("execution(* add*(..))")
    @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice() {
        System.out.println("\n====>>> Executing @Before advice on the following method");
    }
}
