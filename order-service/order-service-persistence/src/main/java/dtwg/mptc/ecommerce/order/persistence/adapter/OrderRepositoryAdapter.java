package dtwg.mptc.ecommerce.order.persistence.adapter;

import dtwg.mptc.ecommerce.domain.entity.Order;
import dtwg.mptc.ecommerce.domain.port.output.OrderRepository;
import dtwg.mptc.ecommerce.order.persistence.entity.OrderEntity;
import dtwg.mptc.ecommerce.order.persistence.mapper.OrderPersistenceMapper;
import dtwg.mptc.ecommerce.order.persistence.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private  final OrderJpaRepository orderJpaRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;


    @Override
    public Order saveOrder(Order order) {
        OrderEntity orderEntity = orderPersistenceMapper.orderToOrderEntity(order);

        orderEntity.getOrderAddress().setOrder(orderEntity);

        orderEntity.getItems().forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));

        // Map OrderEntity to Order
        OrderEntity savOrderEntity = orderJpaRepository.save(orderEntity);
        return orderPersistenceMapper.orderEntityToOrder(orderEntity);


    }
}
