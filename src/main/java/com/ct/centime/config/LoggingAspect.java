package com.ct.centime.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Component
@Aspect
public class LoggingAspect {

    private final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

    @Around(value = "@within(com.ct.centime.configuration.LogMethodParam) || @annotation(com.ct.centime.configuration.LogMethodParam)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        LogMethodParam loggableMethod = method.getAnnotation(LogMethodParam.class);

        if(Objects.nonNull(loggableMethod) && proceedingJoinPoint.getArgs() != null && proceedingJoinPoint.getArgs().length > 0){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < proceedingJoinPoint.getArgs().length; i++) {
                sb.append(method.getParameterTypes()[i].getName() + ":" + proceedingJoinPoint.getArgs()[i]);
                if (i < proceedingJoinPoint.getArgs().length - 1)
                    sb.append(", ");
            }

            LOG.info("Class name : " + proceedingJoinPoint.getTarget().getClass() + "Method name : " + method.getName() + "() args " + sb);
        }

        return proceedingJoinPoint.proceed();

    }
}
