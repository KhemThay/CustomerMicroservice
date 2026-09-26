package dtwg.mptc.ecommerce.order.domain.port.output;

import dtwg.mptc.ecommerce.domain.entity.Order;

public interface OrderRepository {
    void save(Order order);
}
