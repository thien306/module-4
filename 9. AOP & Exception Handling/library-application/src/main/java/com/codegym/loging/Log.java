package com.codegym.loging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class Log {
    private int count = 0;

    @Pointcut("within(com.codegym.controller.BookController.*)")
    public void allMethod() {
    }

    @Pointcut("execution(* com.codegym.controller.BookController.showAll(..))")
    public void showMethod() {
    }

    @Pointcut("execution(* com.codegym.controller.BookController.change*(..))")
    public void changeMethod() {

    }

    @Before("allMethod()")
    public void beforeCallMethod(JoinPoint joinPoint) {
        System.out.println("Start Method name" +
                joinPoint.getSignature().getName() +
                " Time: " + LocalDateTime.now());
    }

    @After("allMethod()")
    public void afterCallMethod(JoinPoint joinPoint) {
        System.out.println("End Method name" +
                joinPoint.getSignature().getName() +
                " Time: " + LocalDateTime.now());
    }

    @After("showMethod()")
    public void afterCallShowMethod(JoinPoint joinPoint) {
        count++;
        System.out.println("End Method name " +
                joinPoint.getSignature().getName() +
                " Time: " + LocalDateTime.now());
        System.out.println("Number of visits : " + count);
    }

    @AfterThrowing("allMethod()")
    public void afterException(JoinPoint joinPoint) {
        System.out.println("Exception in : " +
                joinPoint.getSignature().getName() +
                "Time : " + LocalDateTime.now());
    }

    @Before("changeMethod()")
    public void beforeChange(JoinPoint joinPoint) {
        System.out.println("Start Change" +
                joinPoint.getSignature().getName() +
                "Time : " + LocalDateTime.now());
    }

    @AfterReturning("changeMethod()")
    public void afterChange(JoinPoint joinPoint) {
        System.out.println("Change success!" +
                joinPoint.getSignature().getName() +
                "Time : " + LocalDateTime.now());
    }
}
