package dtwg.mptc.ecommerce.order.persistence.adaptor;

import dtwg.mptc.ecommerce.domain.entity.Order;
import dtwg.mptc.ecommerce.order.domain.port.output.OrderRepository;
import dtwg.mptc.ecommerce.order.persistence.entity.OrderEntity;
import dtwg.mptc.ecommerce.order.persistence.mapper.OrderPersistenceMapper;
import dtwg.mptc.ecommerce.order.persistence.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryAdaptor implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;

    @Override
    public Order saveOrder(Order order){
        //Map Order to OrderEntity
        OrderEntity orderEntity = orderPersistenceMapper.orderToOrderEntity(order);

        if (orderEntity.getOrderAddress() != null) {
            orderEntity.getOrderAddress().setOrder(orderEntity);
        }
        if (orderEntity.getItems() != null) {
            orderEntity.getItems().forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));
        }

        OrderEntity saveOrderEntity = orderJpaRepository.save(orderEntity);

        //Map OrderEntity to Order
        return orderPersistenceMapper.orderEntityToOrder(saveOrderEntity);
    }
}
