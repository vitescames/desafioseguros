package br.com.itau.desafioseguros.infrastructure.crosscutting.log;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RestExceptionHandlerLogger {

    Logger logger = LoggerFactory.getLogger(RestExceptionHandlerLogger.class);

    @Before(value = "execution(* br.com.itau.desafioseguros.entrypoint.api.exception.RestExceptionHandler.*(..)) && args(exception,..)")
    public void beforeRestExceptionHandler(Exception exception) {
        logger.error("Houve um erro ao executar uma das API's REST", exception);
    }
}
