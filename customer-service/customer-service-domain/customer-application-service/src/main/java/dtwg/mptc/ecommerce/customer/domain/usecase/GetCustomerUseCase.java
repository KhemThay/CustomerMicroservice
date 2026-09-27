package dtwg.mptc.ecommerce.customer.domain.usecase;

import dtwg.mptc.ecommerce.customer.domain.dto.CustomerResult;
import dtwg.mptc.ecommerce.customer.domain.dto.GetCustomerQuery;
import dtwg.mptc.ecommerce.customer.domain.exception.CustomerNotFoundException;
import dtwg.mptc.ecommerce.customer.domain.mapper.CustomerDataMapper;
import dtwg.mptc.ecommerce.customer.domain.port.output.CustomerRepository;
import dtwg.mptc.ecommerce.domain.valueobject.CustomerId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class GetCustomerUseCase {

    private final CustomerRepository customerRepository;
    private final CustomerDataMapper customerDataMapper;

    @Transactional(readOnly = true)
    public CustomerResult execute(GetCustomerQuery getCustomerQuery) {
        log.info("Execute GetCustomerUseCase : {}", getCustomerQuery);

        return customerRepository.findById(new CustomerId(getCustomerQuery.customerId()))
                .map(customerDataMapper::customerToCustomerResult)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found: " + getCustomerQuery.customerId()));
    }
}
