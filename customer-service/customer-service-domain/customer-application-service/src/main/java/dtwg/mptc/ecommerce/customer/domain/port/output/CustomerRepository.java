package dtwg.mptc.ecommerce.customer.domain.port.output;


import dtwg.mptc.ecommerce.domain.valueobject.CustomerId;
import dtwg.mptc.ecommerce.entity.Customer;

import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(CustomerId customerId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
