package br.com.itau.desafioseguros.infrastructure.crosscutting.loggers;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InsuranceProductControllerLogger {

    Logger logger = LoggerFactory.getLogger(InsuranceProductControllerLogger.class);

    @Around("execution(* br.com.itau.desafioseguros.entrypoint.api.controller.InsuranceProductController.*(..))")
    public Object logAroundInsuranceProductController(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        MDC.put("context", className.substring(className.lastIndexOf('.') + 1) + "." + methodName);

        logger.info(">> {}() - {}", methodName, joinPoint.getArgs());

        Object result = joinPoint.proceed();

        logger.info("<< {}() - {}", methodName, result);

        return result;
    }

}
