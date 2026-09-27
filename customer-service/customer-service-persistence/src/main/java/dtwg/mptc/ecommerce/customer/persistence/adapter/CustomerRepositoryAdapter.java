package dtwg.mptc.ecommerce.customer.persistence.adapter;

import dtwg.mptc.ecommerce.customer.domain.port.output.CustomerRepository;
import dtwg.mptc.ecommerce.customer.persistence.entity.CustomerEntity;
import dtwg.mptc.ecommerce.customer.persistence.mapper.CustomerPersistenceMapper;
import dtwg.mptc.ecommerce.customer.persistence.repository.CustomerJpaRepository;
import dtwg.mptc.ecommerce.domain.valueobject.CustomerId;
import dtwg.mptc.ecommerce.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {
    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerPersistenceMapper customerPersistenceMapper;

    @Override
    public Customer save(Customer customer) {
        CustomerEntity savedEntity = customerJpaRepository.save(
                customerPersistenceMapper.customerToCustomerEntity(customer));
        return customerPersistenceMapper.customerEntityToCustomer(savedEntity);
    }

    @Override
    public Optional<Customer> findById(CustomerId customerId) {
        return customerJpaRepository.findById(customerId.value())
                .map(customerPersistenceMapper::customerEntityToCustomer);
    }

    @Override
    public boolean existsByUsername(String username) {
        return customerJpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerJpaRepository.existsByEmail(email);
    }
}
