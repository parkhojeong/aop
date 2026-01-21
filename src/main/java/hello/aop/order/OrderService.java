package hello.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Object orderItem(String itemId) {
        log.info("[orderService] 실행");
        orderRepository.save(itemId);

        Object o = new Object();
        log.info("[orderService] result={}", o);
        return o;
    }
}
