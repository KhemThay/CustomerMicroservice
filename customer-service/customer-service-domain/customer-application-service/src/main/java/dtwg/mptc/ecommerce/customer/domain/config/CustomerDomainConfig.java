package dtwg.mptc.ecommerce.customer.domain.config;

import dtwg.mptc.ecommerce.service.CustomerDomainService;
import dtwg.mptc.ecommerce.service.CustomerDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// customer-domain-core has no Spring annotations, so we register its service as a bean here
@Configuration
public class CustomerDomainConfig {

    @Bean
    public CustomerDomainService customerDomainService() {
        return new CustomerDomainServiceImpl();
    }
}
