package io.volkan.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(getClass().getName());

    // The pointcut is: execution of all setXX methods that take one argument and
    // return void within io.volkan package.
    @Before("execution(void io.volkan..*.set*(*))") // <- that‘s the pointcut expression language.
    public void callSetters(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String arg = joinPoint.getArgs()[0].toString();
        logger.info(String.format("Setters called %s %s on %s", methodName, arg, joinPoint.getTarget()));
    }
}
