package dtwg.mptc.ecommerce.order.persistence.adaptor;

import dtwg.mptc.ecommerce.domain.entity.Order;
import dtwg.mptc.ecommerce.order.domain.port.output.OrderRepository;
import dtwg.mptc.ecommerce.order.persistence.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryAdaptor implements OrderRepository {

    private  final OrderJpaRepository orderJpaRepository;

    @Override
    public void save(Order order) {

    }
}
