package br.com.itau.desafioseguros.crosscutting.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* br.com.itau.desafioseguros.adapters.entrypoint..*.*(..))")
    public Object logAroundEntrypoints(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        MDC.put("context", className.substring(className.lastIndexOf('.') + 1) + "." + methodName);

        logger.info(">> {}() - {}", methodName, joinPoint.getArgs());

        Object result = joinPoint.proceed();

        logger.info("<< {}() - {}", methodName, result);

        return result;
    }

    @Around("execution(* br.com.itau.desafioseguros.application.command.handlers..*.*(..))")
    public Object logAroundCommandHandlers(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();

        logger.info(">> {}()", methodName);

        Object result = joinPoint.proceed();

        logger.info("<< {}() - {}", methodName, result);

        return result;
    }

}
