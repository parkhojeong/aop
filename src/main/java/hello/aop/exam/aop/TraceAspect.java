package hello.aop.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Slf4j
public class TraceAspect {
    @Before("@annotation(hello.aop.exam.annotation.Trace)")
    public void logAspect(JoinPoint joinPoint) {
        log.info("logAspect joinPoint={}, arg={}", joinPoint.getSignature(), joinPoint.getArgs());
    }
}
