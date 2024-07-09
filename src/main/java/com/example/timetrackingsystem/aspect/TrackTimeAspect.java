package com.example.timetrackingsystem.aspect;

import com.example.timetrackingsystem.service.impl.TimeTrackerImpl;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Аспект для отслеживания времени выполнения методов, помеченных аннотацией {@link com.example.timetrackingsystem.annotation.TrackTime}.
 * <p>
 * Этот аспект используется для синхронного отслеживания времени выполнения методов.
 * Методы, помеченные аннотацией {@link com.example.timetrackingsystem.annotation.TrackTime}, будут выполнены с отслеживанием времени.
 * </p>
 */
@Component
@Aspect
@RequiredArgsConstructor
public class TrackTimeAspect {

    private final TimeTrackerImpl timeTracker;

    @Pointcut("@annotation(com.example.timetrackingsystem.annotation.TrackTime)")
    public void trackTimePointcut() {
    }

    @Around("trackTimePointcut()")
    public Object trackTime(ProceedingJoinPoint proceedingJoinPoint) {
        return timeTracker.trackTime(proceedingJoinPoint, "SYNCHRONOUS");
    }
}
