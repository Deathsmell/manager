package com.steis.manager.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAspect {

    private final static Logger logger = Logger.getLogger(LogAspect.class);

    private Object logMethodInvocation (ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder info = new StringBuilder();
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        info
                .append(joinPoint.getTarget().getClass().getSimpleName()).append(".")
                .append("(").append(Arrays.toString(joinPoint.getArgs())).append(")")
                .append(" : ").append(result)
                .append(" in ").append(System.currentTimeMillis() - start).append(" msec.");
        logger.info(info);
        return result;
    }

    @Around("execution(* com.steis.manager.service.*.*(..))")
    public Object logService (ProceedingJoinPoint joinPoint)throws Throwable {
        return logMethodInvocation(joinPoint);
    }
}
