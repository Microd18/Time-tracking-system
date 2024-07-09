package com.example.timetrackingsystem.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Аспект для логирования выполнения методов, а также обработки исключений.
 * <p>
 * Этот аспект содержит три основных точки среза:
 * <ul>
 *     <li>Методы контроллеров (в пакетах <code>com.example.timetrackingsystem.controller</code>)</li>
 *     <li>Методы сервисов (в пакетах <code>com.example.timetrackingsystem.service</code>)</li>
 *     <li>Методы, аннотированные @ExceptionHandler</li>
 * </ul>
 * </p>
 * <p>
 * Для методов контроллеров и сервисов логируются имена методов, аргументы и результаты выполнения. Для методов обработки исключений логируются выброшенные исключения и аргументы.
 * </p>
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.example.timetrackingsystem.controller..*(..))")
    public void controllerMethodsPointcut() {
    }

    @Pointcut("execution(* com.example.timetrackingsystem.service..*(..))")
    public void serviceMethodsPointcut() {
    }

    @Pointcut("execution(@org.springframework.web.bind.annotation.ExceptionHandler * *(..))")
    public void exceptionPointcut() {
    }


    @Around("controllerMethodsPointcut()")
    public Object aroundControllerMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] methodArgs = proceedingJoinPoint.getArgs();

        log.info("Выполнение метода {} с аргументами {}", methodName, methodArgs);

        Object result = proceedingJoinPoint.proceed();

        log.info("Метод {} завершил выполнение {}", methodName, result);

        return result;
    }

    @Around("exceptionPointcut()")
    public Object afterExceptionHandler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] methodArgs = proceedingJoinPoint.getArgs();

        Object result = proceedingJoinPoint.proceed();

        log.error("Выброшено исключение: {} - {} - {}", methodName, result, methodArgs);

        return result;
    }
}
