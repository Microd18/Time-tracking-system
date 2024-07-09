package com.example.timetrackingsystem.aspect;

import com.example.timetrackingsystem.exceptions.AirlineNotFoundException;
import com.example.timetrackingsystem.exceptions.ExecutionTimeTrackingException;
import com.example.timetrackingsystem.exceptions.PlaneNotFoundException;
import com.example.timetrackingsystem.service.impl.TimeTrackerImpl;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Аспект для отслеживания времени выполнения методов, помеченных аннотацией {@link com.example.timetrackingsystem.annotation.TrackAsyncTime}.
 * <p>
 * Этот аспект позволяет выполнять методы асинхронно, используя {@link CompletableFuture}, и отслеживать время их выполнения.
 * </p>
 */

@Component
@Aspect
@RequiredArgsConstructor
public class TrackAsyncTimeAspect {

    private final TimeTrackerImpl timeTracker;

    @Pointcut("@annotation(com.example.timetrackingsystem.annotation.TrackAsyncTime)")
    public void trackAsyncTimePointcut() {
    }

    @Around("trackAsyncTimePointcut()")
    public Object trackAsyncTime(ProceedingJoinPoint proceedingJoinPoint) {
        CompletableFuture<Object> completableFuture = CompletableFuture.supplyAsync(() -> timeTracker.trackTime(proceedingJoinPoint, "ASYNCHRONOUS"));

        try {
            return completableFuture.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ExecutionTimeTrackingException();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof AirlineNotFoundException) {
                throw (AirlineNotFoundException) cause;
            } else if (cause instanceof PlaneNotFoundException) {
                throw (PlaneNotFoundException) cause;
            } else {
                throw new ExecutionTimeTrackingException();
            }
        }
    }
}
