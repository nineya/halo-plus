package com.nineya.haloplus.aspect;

import com.nineya.haloplus.config.properties.HaloProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.nineya.haloplus.annotation.DisableOnCondition;
import com.nineya.haloplus.exception.ForbiddenException;
import com.nineya.haloplus.model.enums.Mode;

/**
 * 自定义注解DisableApi的切面
 *
 * @author guqing
 * @date 2020-02-14 14:08
 */
@Aspect
@Slf4j
@Component
public class DisableOnConditionAspect {

    private final HaloProperties haloProperties;

    public DisableOnConditionAspect(HaloProperties haloProperties) {
        this.haloProperties = haloProperties;
    }

    @Pointcut("within(com.nineya.haloplus.controller..*)")
    public void pointcut() {
    }

    @Around("pointcut() && @annotation(disableApi)")
    public Object around(ProceedingJoinPoint joinPoint,
        DisableOnCondition disableApi) throws Throwable {
        Mode mode = disableApi.mode();
        if (haloProperties.getMode().equals(mode)) {
            throw new ForbiddenException("禁止访问");
        }

        return joinPoint.proceed();
    }
}
