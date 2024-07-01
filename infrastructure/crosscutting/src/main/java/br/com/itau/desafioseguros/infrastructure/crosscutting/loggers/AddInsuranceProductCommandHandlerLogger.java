package br.com.itau.desafioseguros.infrastructure.crosscutting.loggers;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AddInsuranceProductCommandHandlerLogger {

    Logger logger = LoggerFactory.getLogger(AddInsuranceProductCommandHandlerLogger.class);

    @Around("execution(* br.com.itau.desafioseguros.application.command.handlers.AddInsuranceProductCommandHandler.*(..))")
    public Object logAroundAddInsuranceProductCommandHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();

        logger.info(">> {}()", methodName);

        Object result = joinPoint.proceed();

        logger.info("<< {}() - {}", methodName, result);

        return result;
    }

}
