package com.feature.gcoin.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogginAspect {
	
	@Around("execution(* com.feature.gcoin.service.*.*(..))")
	public Object userAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("[START]-Service: " + joinPoint.getSignature().getDeclaringTypeName() + " : " );
		Long t1 = System.currentTimeMillis();
		Object value = joinPoint.proceed();
		System.out.println("[END]-Service: " + joinPoint.getSignature().getDeclaringTypeName() + " : " + (System.currentTimeMillis() - t1));
		
		return value;
	}
	
	@Around("execution(* com.feature.gcoin.controller.*.*(..))")
	public Object userAdviceController(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("[START]-controller: " + joinPoint.getSignature().getDeclaringTypeName() + " : ");
		Long t1 = System.currentTimeMillis();
		Object value = joinPoint.proceed();
		System.out.println("[END]-controller: " + joinPoint.getSignature().getDeclaringTypeName() + " : " + (System.currentTimeMillis() - t1));
		
		return value;
	}
}
