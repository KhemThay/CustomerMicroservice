package dtwg.mptc.ecommerce.domain.service;


import dtwg.mptc.ecommerce.domain.entity.Business;
import dtwg.mptc.ecommerce.domain.entity.Order;
import dtwg.mptc.ecommerce.domain.event.OrderCancelledEvent;
import dtwg.mptc.ecommerce.domain.event.OrderCreatedEvent;
import dtwg.mptc.ecommerce.domain.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Business business);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);

}
