package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {
    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // jdk dynamic proxy - based on interface

        MemberService cast1 = (MemberService) proxyFactory.getProxy();
        log.info("cast1={}", cast1.getClass());

        // cast interface -> ClassCastException
        Assertions.assertThatThrownBy(() -> {
            MemberServiceImpl cast2 = (MemberServiceImpl) proxyFactory.getProxy();
        }).isInstanceOf(ClassCastException.class);
    }

    // CGLIB Proxy extends MemberServiceImpl
    // MemberServiceImpl implements MemberService
    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // cglib proxy - based on concrete class

        MemberService cast1 = (MemberService) proxyFactory.getProxy();
        log.info("cast1={}", cast1.getClass());

        MemberServiceImpl cast2 = (MemberServiceImpl) proxyFactory.getProxy();
        log.info("cast2={}", cast2.getClass());
    }
}
