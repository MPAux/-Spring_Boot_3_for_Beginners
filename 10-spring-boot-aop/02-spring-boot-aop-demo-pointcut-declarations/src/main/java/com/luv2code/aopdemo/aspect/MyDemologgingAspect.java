package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemologgingAspect {

    @Around("execution(* com.luv2code.aopdemo.service.TrafficFortuneService.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws  Throwable {

        // print out method we are advising on
        System.out.println("\n====> Executing @Around on method: " + theProceedingJoinPoint.getSignature().toShortString());

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now let's execute the method
        Object result = null;
        try {
            result= theProceedingJoinPoint.proceed();
        } catch (Exception e) {
            // log the exception
            System.out.println(e.getMessage());

            // give user a custom message
            //result = "Major accident! But don't worry, your AOP helicopter is on its way.";

            // instead we are gonna rethrow the exception
            throw e;

        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and disply it
        System.out.println("\nTotal duration = " + (end-begin)/1000.0 + " seconds");
        return result;
    }

    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
        // print out which method we are advising on
        System.out.println("\n====> Executing @After (finally) on method: " +
                theJoinPoint.getSignature().toShortString());
    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing= "theExc"

    )
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
        // print out which method we are advising on
        // print out which method we are advising on
        System.out.println("\n====> Executing after throwing on method: " +
                theJoinPoint.getSignature().toShortString());
        // log the exception
        System.out.println("The exception is: " + theExc);
    }

    // add a new advice for @AfterReturning on the findAccounts method
    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindaccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
        // print out which method we are advising on
        System.out.println("\n====> Executing after returning on method: " +
                theJoinPoint.getSignature().toShortString());
        // print out the results of the method call
        System.out.println("\n====> Result is: " + result);

        // let´s post-process the data ... let´s modify it :-)

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        for(Account tempAccount: result) {
            tempAccount.setName(tempAccount.getName().toUpperCase());
        }
    }

    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n====>>> Executing @Before advice on the following method");

        // display the method signature
        System.out.println("Method signature: " + theJoinPoint.getSignature());
        // display the method arguments
        List<Object> arguments = Arrays.asList(theJoinPoint.getArgs());
        for(Object argument : arguments) {
            if(argument instanceof Account) {
                System.out.println("Method argument: " + ((Account) argument).getName() + "/" + ((Account) argument).getLevel());
            } else {
                System.out.println("Method argument: " + argument);
            }
        }

    }

}
