package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(CallLogAspect.class)
class CallServiceV3Test {
    @Autowired
    CallServiceV3 callServiceV3;

    @Test
    void external() {
        log.info("callServiceV2 = {}", callServiceV3);
        callServiceV3.external();
    }

}