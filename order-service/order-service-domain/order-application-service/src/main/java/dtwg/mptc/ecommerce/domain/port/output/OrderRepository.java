package dtwg.mptc.ecommerce.domain.port.output;


import dtwg.mptc.ecommerce.domain.entity.Order;

public interface OrderRepository {

    Order saveOrder(Order order);
}
