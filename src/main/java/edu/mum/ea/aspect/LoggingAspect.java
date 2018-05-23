package edu.mum.ea.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
	
	// setup logger
	private Logger mylogger = Logger.getLogger(getClass().getName());

	 @Pointcut("execution(* edu.mum.ea.controller.*.*(..))")
 	    public void forControllerpPackage() {}
	 @Pointcut("execution(* edu.mum.ea.service.*.*(..))")
	    public void forServicePackage() {}
	 
	 @Pointcut("execution(* edu.mum.ea.repository.*.*(..))")
	    public void forRepositoryPackage() {}
	 
	 @Pointcut("forControllerpPackage() || forControllerpPackage() || forRepositoryPackage()")
	    public void all() {}
	 @Before("all()")
	 public void beforelogResource(JoinPoint joinPoint) {
		 String myMethod = joinPoint.getSignature().toShortString();
		  mylogger.info("========>>  In @Before : calling method: "+ myMethod);
		  /*Object[] args = joinPoint.getArgs();
		  for(Object x:args) {
			  mylogger.info("=======>> argument:" + x);
		  }*/
	 }
	 
	 //
	 @Around("all()")
	 public Object aroundlogResource(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		 String method = proceedingJoinPoint.getSignature().toShortString();
		 mylogger.info("\n=====>>> Executing @Around : calling method: " + method);
			long begin = System.currentTimeMillis();
			Object result = null;		
			try {
				result = proceedingJoinPoint.proceed();
			} catch (Exception e) {
				// log the exception
				mylogger.warning(e.getMessage());
				
				// rethrow exception
				throw e;
			}
			long end = System.currentTimeMillis();
			
			long duration = end - begin;
			
			mylogger.info("\n=====> Duration: " + duration / 1000.0 + " seconds");
			
			return result;
	 }
	   
}
