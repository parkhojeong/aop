package hello.aop.exam.aop;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class RetryAspect {
    @Around("" +
//            "@annotation(hello.aop.exam.annotation.Retry) && " +
            "@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[retry] {} retry={}", joinPoint.getSignature(), retry);

        Throwable exceptionHolder = null;
        for (int retryCount = 1; retryCount <= retry.value(); retryCount++) {
            try {
                log.info("[retry] try count={}/{}", retryCount, retry.value());
                return joinPoint.proceed();
            } catch (Exception e) {
                log.error("retry error", e);
                exceptionHolder = e;
            }
        }

        throw exceptionHolder;
    }
}
