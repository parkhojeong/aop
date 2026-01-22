package hello.aop.pointcut;

import hello.aop.member.MemberService;
import hello.aop.order.aop.annotation.Anno;
import hello.aop.order.aop.annotation.Param;
import hello.aop.order.aop.annotation.TestClass;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import({AtAnnotationTest.AtAnnotationAspect.class, TestClass.class})
public class AtAnnotationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    TestClass testClass;

    @Test
    void success() {
        log.info("memberService proxy={}", memberService.getClass());
        memberService.hello("ok");
    }

    @Test
    void args() {
        log.info("innerClass proxy={}", testClass.getClass());
        testClass.call(new Param());
    }

    @Slf4j
    @Aspect
    static class AtAnnotationAspect {
        @Around("@annotation(hello.aop.member.annotation.MethodAop)")
        public Object atAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@annotation] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("execution(* hello.aop..*(..)) && @args(hello.aop.order.aop.annotation.Anno)")
        public Object atAnnotationArgs(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@args] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

}