package dtwg.mptc.ecommerce.order.persistence.adaptor;

import dtwg.mptc.ecommerce.domain.entity.Customer;
import dtwg.mptc.ecommerce.order.domain.port.output.CustomerRepository;
import dtwg.mptc.ecommerce.order.persistence.mapper.CustomerPersistenceMapper;
import dtwg.mptc.ecommerce.order.persistence.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class CustomerRepositoryAdaptor implements CustomerRepository {

    private  final CustomerJpaRepository customerJpaRepository;
    private  final CustomerPersistenceMapper customerPersistenceMapper;


    @Override
    public Optional<Customer> findCustomer(UUID customerId) {
        return customerJpaRepository.findById(customerId)
                .map(customerPersistenceMapper::customerEntityToCustomer);
    }
}
