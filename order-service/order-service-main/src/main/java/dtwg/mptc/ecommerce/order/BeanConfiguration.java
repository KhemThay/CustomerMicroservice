package dtwg.mptc.ecommerce.order;

import dtwg.mptc.ecommerce.domain.service.OrderDomainService;
import dtwg.mptc.ecommerce.domain.service.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//How to configuration bean
//1.Annotation base
//2.Java base (method)
@Configuration
public class BeanConfiguration {

    @Bean
    public OrderDomainService orderDomainService(){
        return  new OrderDomainServiceImpl();
    }
}
