package dtwg.mptc.ecommerce.order.restapi.mapper;

import dtwg.mptc.ecommerce.domain.entity.Customer;
import dtwg.mptc.ecommerce.order.restapi.dto.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerWebMapper {

    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "giveName", target = "givenName")
    CustomerResponse customerToCustomerResponse(Customer customer);

}

