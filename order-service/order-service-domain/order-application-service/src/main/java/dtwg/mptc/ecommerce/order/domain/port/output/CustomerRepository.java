package dtwg.mptc.ecommerce.order.domain.port.output;

import dtwg.mptc.ecommerce.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);
}
