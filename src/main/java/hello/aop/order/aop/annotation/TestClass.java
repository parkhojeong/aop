package hello.aop.order.aop.annotation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestClass {
    public void call(Param param) {
        log.info("param={}", param);
    }
}
